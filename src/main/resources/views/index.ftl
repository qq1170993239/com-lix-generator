<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>代码搬运工</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css"/>
<script type="text/javascript" src="lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="lib/bootstrap.min.js"></script>
<body>
<div class="jumbotron">
    <h1>代码生成器</h1>
    <p>说明：当前仅支持mysql建表语句</p>
</div>
<div style="width: 60%;margin:0 auto;">
    <div>
        <form class="form-horizontal" role="form" method="post" action="/code">
            <div class="form-group">
                <label for="author" class="col-sm-2 control-label">作者</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="author" name="author" placeholder="作者...">
                </div>
            </div>
            <div class="form-group">
                <label for="packagePath" class="col-sm-2 control-label">包路径</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="packagePath" name="packagePath" placeholder="包路径...">
                </div>
            </div>
            <div class="form-group">
                <label for="sql" class="col-sm-2 control-label">sql</label>
                <div class="col-sm-10">
                    <textarea class="form-control" rows="10" id="sql" name="sql" placeholder="建表语句..."></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnSend" name="btnSend" type="submit" class="btn btn-default">生成代码</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="jumbotron text-center" style="margin-bottom:0">
    <p>Email ： sclx1220@163.com</p>
</div>
</body>
<script>
document.getElementById("sql").value="CREATE TABLE user (\n id  int(11) NOT NULL COMMENT '自增主键',\n name  varchar(16) NULL DEFAULT NULL COMMENT '姓名',\n sex  varchar(2) NULL DEFAULT NULL COMMENT '性别',\n age  int(3) NULL DEFAULT NULL COMMENT '年龄',\n address  varchar(255) NULL DEFAULT NULL COMMENT '住址',\n phone  varchar(32) NULL DEFAULT NULL COMMENT '联系方式',\n PRIMARY KEY ( id ) USING BTREE \n) ENGINE = InnoDB"
</script>
</html>