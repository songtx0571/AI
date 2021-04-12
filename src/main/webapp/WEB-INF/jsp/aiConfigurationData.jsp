<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浩维运行引导管理平台</title>
</head>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/layui/layui.js"></script>
<script type="text/javascript" src="../js/week/alert.js"></script>
<link rel="stylesheet" type="text/css" href="../js/layui/css/layui.css" />
<script type="text/javascript" src="../js/week/aiConfigurationData.js?version=1.11"></script>

<style type="text/css">
    *{
        margin: 0;
        padding: 0;
    }
    .top{
        padding-top: 10px;
        box-sizing: border-box;
    }
    .top #selectDetailsBtn{
        display: none;
    }
    .content{
        padding:  0px 5px;
        box-sizing: border-box;
        display: none;
    }
    body::-webkit-scrollbar{
        display: none;
    }
    .selectDetailsDiv{
        width: 330px;
        margin: 20px auto 0;
        display: none;
    }
</style>
<script type="text/javascript">

</script>
<body>
<div class="warp">
    <div class="top">
        <input type="hidden" id="departListListHidden">
        <input type="hidden" id="addressListHidden">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">设备名称</label>
                    <div class="layui-input-inline" style="margin-right: 0px;">
                        <select name="modules" lay-verify="required" lay-filter="departListList" lay-search="" id="departListList">
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-filter="addressList" lay-search="" id="addressList">
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <button type="button" class="layui-btn" id="selectDetailsBtn" onclick="selectDetails()">搜索</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="selectDetailsDiv">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <input type="hidden" id="measuringPointListHidden">
                <div class="layui-inline" style="margin-bottom: 15px;">
                    <label class="layui-form-label">测点名称</label>
                    <div class="layui-input-inline" style="margin-right: 0px;">
                        <select name="modules" lay-verify="required" lay-filter="measuringPointList" lay-search="" id="measuringPointList">
                            <option value="temp1">temp1</option>
                            <option value="temp2">temp2</option>
                            <option value="temp3">temp3</option>
                            <option value="temp4">temp4</option>
                            <option value="temp5">temp5</option>
                            <option value="temp6">temp6</option>
                            <option value="temp7">temp7</option>
                            <option value="temp8">temp8</option>
                            <option value="temp9">temp9</option>
                            <option value="temp10">temp10</option>
                            <option value="temp11">temp11</option>
                            <option value="temp12">temp12</option>
                            <option value="temp13">temp13</option>
                            <option value="temp14">temp14</option>
                            <option value="temp15">temp15</option>
                            <option value="temp16">temp16</option>
                            <option value="temp17">temp17</option>
                            <option value="temp18">temp18</option>
                            <option value="temp19">temp19</option>
                            <option value="temp20">temp20</option>
                            <option value="temp21">temp21</option>
                            <option value="temp22">temp22</option>
                            <option value="temp23">temp23</option>
                            <option value="temp24">temp24</option>
                            <option value="temp25">temp25</option>
                            <option value="temp26">temp26</option>
                            <option value="temp27">temp27</option>
                            <option value="temp28">temp28</option>
                            <option value="temp29">temp29</option>
                            <option value="temp30">temp30</option>
                            <option value="temp31">temp31</option>
                            <option value="temp32">temp32</option>
                            <option value="temp33">temp33</option>
                            <option value="temp34">temp34</option>
                            <option value="temp35">temp35</option>
                            <option value="temp36">temp36</option>
                            <option value="temp37">temp37</option>
                            <option value="temp38">temp38</option>
                            <option value="temp39">temp39</option>
                            <option value="temp40">temp40</option>
                            <option value="temp41">temp41</option>
                            <option value="temp42">temp42</option>
                            <option value="temp43">temp43</option>
                            <option value="temp44">temp44</option>
                            <option value="temp45">temp45</option>
                            <option value="temp46">temp46</option>
                            <option value="temp47">temp47</option>
                            <option value="temp48">temp48</option>
                            <option value="temp49">temp49</option>
                            <option value="temp50">temp50</option>
                            <option value="temp51">temp51</option>
                            <option value="temp52">temp52</option>
                            <option value="temp53">temp53</option>
                            <option value="temp54">temp54</option>
                            <option value="temp55">temp55</option>
                            <option value="temp56">temp56</option>
                            <option value="temp57">temp57</option>
                            <option value="temp58">temp58</option>
                            <option value="temp50">temp59</option>
                            <option value="temp60">temp60</option>
                            <option value="temp61">temp61</option>
                            <option value="temp62">temp62</option>
                            <option value="temp63">temp63</option>
                            <option value="temp64">temp64</option>
                        </select>
                    </div>
                </div>
                <input type="hidden" id="selStartTime">
                <div class="layui-inline" style="margin-bottom: 15px;">
                    <label class="layui-form-label">开始时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test1" placeholder="年月">
                    </div>
                </div>
                <input type="hidden" id="selEndTime">
                <div class="layui-inline" style="margin-bottom: 15px;">
                    <label class="layui-form-label">结束时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="test2" placeholder="年月">
                    </div>
                </div>
                <div class="layui-inline" style="text-align: center;margin-bottom: 15px;width: 100%;">
                    <button type="button" class="layui-btn" onclick="selOk()">确定</button>&nbsp;&nbsp;<button type="button" class="layui-btn" onclick="cancel()">取消</button>
                </div>
            </div>
        </form>
    </div>
    <div class="content">
        <table id="demo" lay-filter="test"></table>
    </div>
</div>
</div>
</body>
</html>
