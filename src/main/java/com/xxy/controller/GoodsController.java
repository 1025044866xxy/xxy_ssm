package com.xxy.controller;

import com.xxy.model.Goods;
import com.xxy.model.User;
import com.xxy.service.GoodsService;
import com.xxy.util.JsonMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @RequestMapping(value = "goodsList")
    public String goodsList(Integer id, Model model)
    {
        Goods good=goodsService.getGoodInfo(id);
        model.addAttribute("good",good);
        return "good_info";
    }
    @RequestMapping(value = "buygood")
    public String buyaction(Integer id,Model model)
    {
        Goods good=goodsService.getGoodInfo(id);
        int num=goodsService.getGoodsNum(id);
        if(num>=1)
        {goodsService.updateGoodsNum(id);
        good.setGoodsNum(good.getGoodsNum()-1);}
        model.addAttribute("good",good);
        return "redirect:/good?id="+id.toString();
    }
    @RequestMapping("backtohome")
    public String back_to_home(Model model)
    {
        List<Goods> list=goodsService.getAllGoods(0,goodsService.getcount());
        model.addAttribute("productList",list);
        return  "goods_list";
    }
    @RequestMapping(value = "getAllGoods",method = RequestMethod.GET)
    public ModelAndView getAllGoods(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo)
    {
        ModelAndView mv=new ModelAndView("goodsPage");
        int limit=5;
        int offser=(pageNo-1)*limit;
        List<Goods> goods=goodsService.getAllGoods(offser,limit);
        int totalItems = goodsService.getcount();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;
        mv.addObject("goods", goods)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }
    @RequestMapping(value = "update_good/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg update_good(@PathVariable("id") Integer id,Goods goods)
    {
        goods.setId(id);
        int res=goodsService.updategood(goods);
        if(res!=1)
            return JsonMsg.fail().addInfo("good_update_error","修改信息出错");
        return JsonMsg.success();
    }

    @RequestMapping(value = "getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage(){
        int totalItems = goodsService.getcount();
        //获取总的页数
        int temp = totalItems / 5;
        int totalPages = (totalItems % 5 == 0) ? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }
    @RequestMapping(value = "delete_good/{goodid}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg delete_good(@PathVariable("goodid") Integer id){
        int res = 0;
        if (id> 0){
            res = goodsService.delete_good(id);
        }
        if (res != 1){
            return JsonMsg.fail().addInfo("good_delete_error", "员工删除异常");
        }
        return JsonMsg.success();
    }
    @RequestMapping(value = "add_good",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg add_good(Goods goods,HttpServletRequest request, MultipartFile file)throws IOException
    {
        if (!file.isEmpty()) {
            String fileRealName = file.getOriginalFilename();             //获得原始文件名;
            int pointIndex = fileRealName.indexOf(".");                        //点号的位置
            String fileSuffix = fileRealName.substring(pointIndex);             //截取文件后缀
            UUID FileId = UUID.randomUUID();                        //生成文件的前缀包含连字符
            String savedFileName = FileId.toString().replace("-", "").concat(fileSuffix);       //文件存取名
            String savedDir = request.getSession().getServletContext().getRealPath("img"); //获取服务器指定文件存取路径
            File savedFile = new File(savedDir, savedFileName);
            boolean isCreateSuccess = savedFile.createNewFile();
            if (isCreateSuccess) {
                file.transferTo(savedFile);
                goods.setGoodsModel("img\\"+savedFileName);//转存文件
            }
            //model.addAttribute("save",savedDir);
            // model.addAttribute("flag",true);
        }
        int res=0;
        res=goodsService.insertGood(goods);
        if (res != 1){
            return JsonMsg.fail().addInfo("good_add_error", "员工添加异常");
        }
        return JsonMsg.success();
    }
    @RequestMapping(value = "getGoodById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getGoodById(@PathVariable("id") Integer id)
    {
        Goods goods=goodsService.getGoodInfo(id);
        if(goods!=null)
            return JsonMsg.success().addInfo("good",goods);
        return JsonMsg.fail();
    }
    @RequestMapping("good")
    public String good_info(Model model,Integer id)
    {
        Goods goods=goodsService.getGoodInfo(id);
        model.addAttribute("good",goods);
        return "good_info";
    }

}
