<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods Update Page</title>
</head>
<body>
<div class="modal fade emp-edit-modal" tabindex="-1" role="dialog" aria-labelledby="emp-edit-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">商品更改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal update_emp_form">
                    <div class="form-group">
                        <label  for="update_goodsname" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="goodsName" class="form-control" id="update_goodsname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_goodsnum" class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-8">
                            <input type="text" name="goodsNum" class="form-control" id="update_goodsnum">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_goodsprice" class="col-sm-2 control-label">单价</label>
                        <div class="col-sm-8">
                            <input type="text" name="goodsPrice" class="form-control" id="update_goodsprice">
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary good_update_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




<script type="text/javascript">
    <!-- ==========================修改操作=================================== -->
    $(".good_edit_btn").click(function () {
        //1 获取点击修改员工的id与name;
        var id = $(this).parent().parent().find("td:eq(0)").text();

        //2 根据id或name查询出对应员工信息进行回显；
        $.ajax({
            url:"getGoodById/"+id,
            type:"GET",
            success:function (result) {
                if (result.code == 100){
                    var emp = result.extendInfo.good;
                    console.log(emp);
                    $("#update_goodsname").val(emp.goodsName)
                    $("#update_goodsnum").val(emp.goodsNum);
                    $("#update_goodsprice").val(emp.goodsPrice);
                }
            }

        });


        $(".good_update_btn").attr("id", id);
    });


    $(".good_update_btn").click(function () {
        var id = $(this).attr("id");
        var goodsName=$("#update_goodsname").val();
        var goodsNum=$("#update_goodsnum").val();
        var goodsPrice=$("#update_goodsprice").val();
        console.log(id);
        console.log(goodsName);
        console.log(goodsNum);
        console.log(goodsPrice);

        //5 点击更新按钮，发送AJAX请求到后台进行保存。
        $.ajax({
            url:"update_good/"+id,
            type:"PUT",
            data:$(".update_emp_form").serialize(),
            success:function (result) {
                if (result.code==100){
                    alert("更改成功！");
                    $(".emp-edit-modal").modal("hide");
                    //跳转到当前页
                    var curPage = ${curPage};
                    window.location.href="getAllGoods?pageNo="+curPage;
                }else {
                    alert(result.extendInfo.emp_update_error);
                }
            }
        });

    });
</script>
</body>
</html>
