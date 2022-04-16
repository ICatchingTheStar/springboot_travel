package com.travel.mybootdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.mybootdemo.bean.Category;
import com.travel.mybootdemo.bean.Route;
import com.travel.mybootdemo.bean.UserIsFavorite;
import com.travel.mybootdemo.service.CategoryService;
import com.travel.mybootdemo.service.FavoriteService;
import com.travel.mybootdemo.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 王嵩涛
 * @QQ: 2749391385
 * @CreateTime: 2021-08-14-09-28
 * @Description: 只是页面展示的控制器
 */
@Controller
@Api(tags = "页面展示控制器")
public class pageDisplayController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    RouteService routeService;
    @Autowired
    FavoriteService favoriteService;

    /**
     * 起始页面
     * @param session
     * @return
     */
    @GetMapping(value = {"/index","/"})
    @ApiOperation(value = "初始页面",notes = "展示初始页面")
    public String indexPage(HttpSession session){
        //首先加载起始页时，去分类表中升序查询数据，发送给前台的分类导航栏
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().orderByAsc("cid"));

        //该list集合里存放着分类表中所有category对象，属性为cid和cname
        //因为是公共页面的数据展示，且数据大概率不会经常变动，此处可以考虑存入nosql数据库来提升性能
        session.setAttribute("categoryList",categoryList);

        return "index";
    }

    /**
     * 旅游路线分类展示
     * @param cid       路线所属分类
     * @param rname     搜索内容
     * @param pn        分页时当前页数
     * @param model
     * @return
     */
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pn",value = "当前页码",required = true,type = "Integer"),
                    @ApiImplicitParam(name = "ps",value = "每页条数",required = true,type = "Integer")
            }
    )
    @GetMapping("/route_list")
    @ApiOperation(value = "旅游路线页面",notes = "旅游路线分类展示")
    public String routeList(@RequestParam(value = "cid",required = false) Integer cid,
                            @RequestParam(value = "rname",required = false) String rname,
                            @RequestParam(value = "pn",defaultValue = "1") Integer pn,
                            Model model){
        //如果搜索栏内容为空，则执行此分页查询方法
        if (rname == null){
            Page<Route> routePage = new Page<>(pn,8);
            Page<Route> page = routeService.page(routePage,new QueryWrapper<Route>().eq("cid",cid));
            model.addAttribute("routeList",page);
        }else {
            //如果有内容，则把内容回显到前台页面，执行带条件的分页模糊查询
            Page<Route> routePage = new Page<>(pn, 8);
            Page<Route> page = routeService.page(routePage,new QueryWrapper<Route>().like("rname","%"+rname+"%").eq("cid",cid));
            model.addAttribute("selectRname",rname);
            model.addAttribute("routeList",page);
        }
        //无论是何种分页查新，路线的分类cid都要回显
        model.addAttribute("routeListId", cid);
        return "route_list";
    }

    /**
     * 某个旅游路线的详情信息
     * @param rid       旅游路线独有的id
     * @param pn        用户回退时应回到的页码数
     * @param rname     用户回退时应回到的搜索条件
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/route_detail")
    @ApiOperation(value = "具体路线详情",notes = "查看具体路线信息")
    public String routeDetail(@RequestParam(value = "rid") Integer rid,
                              @RequestParam(value = "pn",defaultValue = "1") Integer pn,
                              @RequestParam(value = "rname",required = false) String rname,
                              Model model,
                              HttpSession session){
        //用户收藏时需要判断的变量
        int uid = -1;
        Boolean isFavorite = false;
        //调用findOne方法，得到经过处理后的旅游路线详情对象
        Route routeById = routeService.findOne(rid);

        //此时，根据用户是否登录，会有两种情形
        UserIsFavorite loginUser = (UserIsFavorite)session.getAttribute("loginUser");//获取用户的登录状态
        //如果用户已经登录
        if (loginUser!=null){
            //根据用户uid和路线的rid查询用户是否收藏过这个路线
            uid = loginUser.getUid();
            boolean flag = routeService.findFavorite(rid,uid);
            isFavorite = flag;
        }

        Category category = categoryService.getById(routeById.getCid());
        //根据isFavorite值判断用户是否收藏过，true为收藏过，false为没收藏和没登录状态
        model.addAttribute("userIsFavorite",isFavorite);
        //除了-1之外的所有情况，都是登录用户的uid
        model.addAttribute("uidFavorite",uid);
        //完整的旅游路线详情
        model.addAttribute("routeById",routeById);

        //以下为用户回退页面回到点击前状态所需参数
        model.addAttribute("Category",category);//该路线详情的所属分类
        model.addAttribute("SelectRname",rname);//搜索条件
        model.addAttribute("PageCurrent",pn);//该路线所在分页
        return "/route_detail";
    }

    /**
     * 用于点击收藏按钮时，判断用户是否登录及进行收藏相关操作
     * @param rid       判断应收藏哪个路线的rid
     * @param uid       判断是哪位用户收藏的uid
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/userIsFavorite")
    @ApiOperation(value = "收藏旅行路线",notes = "收藏旅行路线")
    public String userIsFavorite(@RequestParam(value = "rid") Integer rid,
                                 @RequestParam(value = "uid") Integer uid,
                                 Model model,
                                 HttpSession session){
        UserIsFavorite loginUser = (UserIsFavorite)session.getAttribute("loginUser");
        //如果用户没登录，则uid是-1
        if (uid == -1 || loginUser == null){
            model.addAttribute("msg","您尚未登录，请先登录。");
            return "login";
        }else {
            favoriteService.addFavorite(rid,uid);
            routeService.addCount(rid);
            int cid = routeService.getOne(new QueryWrapper<Route>().eq("rid", rid)).getCid();
//            @{/route_detail(cid=${routeListId},rid=${routelist.getRid()})}
            return "redirect:/route_detail?cid="+cid+"&rid="+rid;
        }
    }

    /**
     * 按照被收藏的次数，展示所有旅游分页路线
     * @param pn        分页的当前页码数
     * @param rname     搜索名字
     * @param low       搜索最低金额
     * @param high      搜索最高金额
     * @param model
     * @return
     */
    @ApiImplicitParams(
            {
            @ApiImplicitParam(name = "pn",value = "当前页码",required = true,type = "Integer"),
            @ApiImplicitParam(name = "ps",value = "每页条数",required = true,type = "Integer")
            }
    )
    @GetMapping("/favoriterank")
    @ApiOperation(value = "热门路线",notes = "热门路线展示")
    public String favoriteRankPage(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
                                   @RequestParam(value = "rname",required = false) String rname,
                                   @RequestParam(value = "low",required = false) Double low,
                                   @RequestParam(value = "high",required = false) Double high,
                                   Model model){

        Page<Route> page = routeService.page(new Page<Route>(pn, 8), new QueryWrapper<Route>().orderByDesc("count"));
        model.addAttribute("routeList",page);
        return "favoriterank";
    }
}
