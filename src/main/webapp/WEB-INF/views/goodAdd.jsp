<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Add Page</title>
</head>
<body>
<div class="modal fade emp-add-modal" tabindex="-1" role="dialog" aria-labelledby="emp-add-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">商品新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_emp_form" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="add_inputName" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="goodsName" class="form-control" id="add_inputName" placeholder="">

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_inputNum" class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-8">
                            <input type="text" name="goodsNum" class="form-control" id="add_inputNum" placeholder="">

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_inputPrice" class="col-sm-2 control-label">单价</label>
                        <div class="col-sm-8">
                            <input type="text" name="goodsPrice" class="form-control" id="add_inputPrice" placeholder="">
                        </div>
                    </div>
                    <%--<div class="form-group">
                        <label for="add_inputPrice" class="col-sm-2 control-label"></label>
                        <div class="col-sm-8">
                            <input type=hidden  name="goodsModel" value="" >
                        </div>
                    </div>--%>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择模型</label>
                        <div class="col-sm-8">
                            <input type="file" name="goodsModel" class="form-control" id="upload" placeholder="">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary emp_save_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">

    <!-------------------------------------新增操作-------------------------------------->
    //=======0 点击 员工新增按钮，发送AJAX请求查询部门列表信息，弹出模态框，
    // 将查询得到的部门列表信息显示在对应模态框中部门信息处。=============================
    $(".book_add").click(function () {
        $('.emp-add-modal').modal({
            backdrop:static,
            keyboard:true
        });
    });

    //=========1 当鼠标从姓名输入框移开的时候，判断姓名输入框内的姓名是否重复 ============
    $(".emp_save_btn").click(function () {
        var goodsName = $("#add_inputName").val();
        var goodsNum = $("#add_inputNum").val();
        var goodsPrice=$("#add_inputPrice").val();
        var formData=new FormData();
        var file=$("#upload")[0].files[0];
        console.log(file)
        formData.append("file",file);
        formData.append("goodsName",goodsName);
        formData.append("goodsNum",goodsNum);
        formData.append("goodsPrice",goodsPrice);
        formData.append("goodsModel","");
        $.ajax({
            url:"add_good",
            type:"POST",
            data:formData,
            // 告诉jQuery不要去处理发送的数据
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            async: false,
            processData: false,
            success:function (result) {
                if (result.code == 100){
                    alert("新增成功");
                    $('#emp-add-modal').modal("hide");
                    //跳往最后一页，由于新增记录，所以要重新查询总页数
                    $.ajax({
                        url:"getTotalPages",
                        type:"GET",
                        success:function (result) {
                            var totalPage = result.extendInfo.totalPages;
                            window.location.href="getAllGoods?pageNo="+totalPage;
                        }
                    })
                } else {
                    alert("新增失败！");
                }
            }

        });
    });



</script>
</body>
</html>
