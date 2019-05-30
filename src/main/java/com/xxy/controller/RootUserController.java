package com.xxy.controller;

import com.sun.tools.hat.internal.model.Root;
import com.xxy.model.Goods;
import com.xxy.service.GoodsService;
import com.xxy.service.RootUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class RootUserController {
    @Autowired
    private RootUserService rootUserService;
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("to_add")
    public String to_add(Model model)
    {
        return "add_good";
    }
    @RequestMapping("add_goods")
    public String add_goods(HttpServletRequest request, Goods goods, Model model, MultipartFile file) throws IOException {
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
            goodsService.insertGood(goods);
            List<Goods> list = goodsService.getAllGoods(0,goodsService.getcount());
            model.addAttribute("productList", list);
            return "root_goods_list";
    }
    /*@RequestMapping("to_update_goodsname")
    public String to_update_goodsname(Integer id,Model model)
    {
        model.addAttribute("id",id);
        return "update_good_name";
    }
    @RequestMapping("to_update_goodsnum")
    public String to_update_goodsnum(Integer id,Model model)
    {
        model.addAttribute("id",id);
        return "update_good_num";
    }
    @RequestMapping("to_update_goodsprice")
    public String to_update_goodsprice(Integer id,Model model)
    {
        model.addAttribute("id",id);
        return "update_good_price";
    }
    @RequestMapping("to_update_goodsmodel")
    public String to_update_goodsmodel(Integer id,Model model)
    {
        model.addAttribute("id",id);
        return "update_good_model";
    }
    @RequestMapping("update_goodsname")
    public String update_goodsname(@Param("id") Integer id, @Param("goodsname") String goodsname, Model model)
    {
        goodsService.updateGoodsName(id,goodsname);
        List<Goods> list = goodsService.getAllGoods();
        model.addAttribute("productList", list);
        return "root_goods_list";
    }
    @RequestMapping("update_goodsnum")
    public String update_goodsnum(Integer id,Integer goodsnum,Model model)
    {
        goodsService.updateGoodsnum(id,goodsnum);
        List<Goods> list = goodsService.getAllGoods();
        model.addAttribute("productList", list);
        return "root_goods_list";
    }
    @RequestMapping("update_goodsprice")
    public String update_goodsprice(Integer id,Double goodsprice,Model model)
    {
        goodsService.updateGoodsPrice(id,goodsprice);
        List<Goods> list = goodsService.getAllGoods();
        model.addAttribute("productList", list);
        return "root_goods_list";
    }
    @RequestMapping("update_goodsmodel")
    public String update_goodsmodel(Integer id,HttpServletRequest request, Model model, MultipartFile file) throws IOException {
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
                goodsService.updateGoodsModel(id,"img\\"+savedFileName);
            }
            //model.addAttribute("save",savedDir);
            // model.addAttribute("flag",true);
        }
        List<Goods> list = goodsService.getAllGoods();
        model.addAttribute("productList", list);
        return "root_goods_list";
    }*/

}
