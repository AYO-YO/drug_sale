<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Store</title>
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th>药品id</th>
        <th>药品名称</th>
        <th>价格</th>
        <th>剩余库存</th>
        <th>生产日期</th>
        <th>保质期（月）</th>
        <th></th>
    </tr>
    </thead>
    <tbody id="store_list"></tbody>
</table>
<script>
    getMeds();
</script>
</body>
</html>