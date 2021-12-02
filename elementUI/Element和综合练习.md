

# 1、Element 基本使用

## 1.1、Element介绍

- Element：网站快速成型工具。是饿了么公司前端开发团队提供的一套基于Vue的网站组件库。

- 使用Element前提必须要有Vue。

- 组件：组成网页的部件，例如超链接、按钮、图片、表格等等~

- Element官网：https://element.eleme.cn/#/zh-CN

- 自己完成的按钮

  ![](.\img\我是按钮.png)

- Element 提供的按钮

  ![](.\img\element提供的按钮.png)

## 1.2、Element快速入门

- **开发步骤**

  1. 下载 Element 核心库。

  2. 引入 Element 样式文件。

  3. 引入 Vue 核心 js 文件。

  4. 引入 Element 核心 js 文件。

  5. 编写按钮标签。

  6. 通过 Vue 核心对象加载元素。

     > #导库
     >
     > a. 引入组件库 (资料/element/element-ui 整个放到webapp路径)
     >
     > b. 引入vue.js

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>快速入门</title>
      <!--
          element组件库基于vue的,先导入vue
      -->
      <script src="../js/vue.js"></script>
      <!-- 引入样式 -->
      <link rel="stylesheet" href="../element-ui/lib/theme-chalk/index.css">
      <!-- 引入组件库 -->
      <script src="../element-ui/lib/index.js"></script>
  
  </head>
  <body>
  <!--
      element组件库: 快速搭建网站效果
  -->
      <div id="div">
          <button>按钮</button>
          <br>
  
          <el-row>
              <el-button>默认按钮</el-button>
              <el-button type="primary">主要按钮</el-button>
              <el-button type="success">成功按钮</el-button>
              <el-button type="info">信息按钮</el-button>
              <el-button type="warning">警告按钮</el-button>
              <el-button type="danger">危险按钮</el-button>
          </el-row>
          <br>
          <el-row>
              <el-button plain>朴素按钮</el-button>
              <el-button type="primary" plain>主要按钮</el-button>
              <el-button type="success" plain>成功按钮</el-button>
              <el-button type="info" plain>信息按钮</el-button>
              <el-button type="warning" plain>警告按钮</el-button>
              <el-button type="danger" plain>危险按钮</el-button>
          </el-row>
          <br>
          <el-row>
              <el-button round>圆角按钮</el-button>
              <el-button type="primary" round>主要按钮</el-button>
              <el-button type="success" round>成功按钮</el-button>
              <el-button type="info" round>信息按钮</el-button>
              <el-button type="warning" round>警告按钮</el-button>
              <el-button type="danger" round>危险按钮</el-button>
          </el-row>
          <br>
          <el-row>
              <el-button icon="el-icon-search" circle></el-button>
              <el-button type="primary" icon="el-icon-edit" circle></el-button>
              <el-button type="success" icon="el-icon-check" circle></el-button>
              <el-button type="info" icon="el-icon-message" circle></el-button>
              <el-button type="warning" icon="el-icon-star-off" circle></el-button>
              <el-button type="danger" icon="el-icon-delete" circle size="mini"></el-button>
          </el-row>
  
      </div>
  </body>
  <script>
      new Vue({
          el:"#div"
      });
  </script>
  </html>
  ```

## 1.3、基础布局

将页面分成最多 24 个部分，自由切分。

![](.\img\基础布局.png)

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>基础布局</title>
      <link rel="stylesheet" href="../element-ui/lib/theme-chalk/index.css">
      <script src="../js/vue.js"></script>
      <script src="../element-ui/lib/index.js"></script>
      <style>
          .el-row {
              /* 行距为20px */
              margin-bottom: 20px;
          }
          .bg-purple-dark {
              background: red;
          }
          .bg-purple {
              background: blue;
          }
          .bg-purple-light {
              background: green;
          }
          .grid-content {
              /* 边框圆润度 */
              border-radius: 4px;
              /* 行高为36px */
              min-height: 36px;
          }
      </style>
  </head>
  <body>
  <!--
      一行最多24份,超过就失效
  -->
      <div id="div">
          <el-row>
              <el-col :span="24"><div class="grid-content bg-purple-dark"></div></el-col>
          </el-row>
          <el-row>
              <el-col :span="12"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="12"><div class="grid-content bg-purple-light"></div></el-col>
          </el-row>
          <el-row>
              <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="8"><div class="grid-content bg-purple-light"></div></el-col>
              <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
          </el-row>
          <el-row>
              <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
          </el-row>
          <el-row>
              <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
              <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
              <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
          </el-row>
  
      </div>
  </body>
  <script>
      new Vue({
          el:"#div"
      });
  </script>
  </html>
  ```

## 1.4、容器布局

将页面分成头部区域、侧边栏区域、主区域、底部区域。

![](.\img\容器布局.png)

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>容器布局</title>
      <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
      <script src="js/vue.js"></script>
      <script src="element-ui/lib/index.js"></script>
      <style>
          .el-header, .el-footer {
              background-color: #d18e66;
              color: #333;
              text-align: center;
              height: 100px;
          }
          .el-aside {
              background-color: #55e658;
              color: #333;
              text-align: center;
              height: 580px;
          }
          .el-main {
              background-color: #5fb1f3;
              color: #333;
              text-align: center;
              height: 520px;
          }
      </style>
  </head>
  <body>
      <div id="div">
          <el-container>
              <el-header>头部区域</el-header>
              <el-container>
                <el-aside width="200px">侧边栏区域</el-aside>
                <el-container>
                  <el-main>主区域</el-main>
                  <el-footer>底部区域</el-footer>
                </el-container>
              </el-container>
            </el-container>
      </div>
  </body>
  <script>
      new Vue({
          el:"#div"
      });
  </script>
  </html>
  ```

## 1.5、表单组件

由输入框、下拉列表、单选框、多选框等控件组成，用以收集、校验、提交数据。

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>表单组件</title>
      <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
      <script src="js/vue.js"></script>
      <script src="element-ui/lib/index.js"></script>
  </head>
  <body>
      <div id="div">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="活动名称" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
              </el-form-item>
              <el-form-item label="活动区域" prop="region">
                <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
                  <el-option label="区域一" value="shanghai"></el-option>
                  <el-option label="区域二" value="beijing"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="活动时间" required>
                <el-col :span="11">
                  <el-form-item prop="date1">
                    <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                  <el-form-item prop="date2">
                    <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>
                  </el-form-item>
                </el-col>
              </el-form-item>
              <el-form-item label="即时配送" prop="delivery">
                <el-switch v-model="ruleForm.delivery"></el-switch>
              </el-form-item>
              <el-form-item label="活动性质" prop="type">
                <el-checkbox-group v-model="ruleForm.type">
                  <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
                  <el-checkbox label="地推活动" name="type"></el-checkbox>
                  <el-checkbox label="线下主题活动" name="type"></el-checkbox>
                  <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="特殊资源" prop="resource">
                <el-radio-group v-model="ruleForm.resource">
                  <el-radio label="线上品牌商赞助"></el-radio>
                  <el-radio label="线下场地免费"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="活动形式" prop="desc">
                <el-input type="textarea" v-model="ruleForm.desc"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
              </el-form-item>
            </el-form>
      </div>
  </body>
  <script>
      new Vue({
          el:"#div",
          data:{
              ruleForm: {
                  name: '',
                  region: '',
                  date1: '',
                  date2: '',
                  delivery: false,
                  type: [],
                  resource: '',
                  desc: ''
                  },
          rules: {
            name: [
              { required: true, message: '请输入活动名称', trigger: 'blur' },
              { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            region: [
              { required: true, message: '请选择活动区域', trigger: 'change' }
            ],
            date1: [
              { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
            ],
            date2: [
              { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
            ],
            type: [
              { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
            ],
            resource: [
              { required: true, message: '请选择活动资源', trigger: 'change' }
            ],
            desc: [
              { required: true, message: '请填写活动形式', trigger: 'blur' }
            ]
          }
          },
          methods:{
              submitForm(formName) {
                  this.$refs[formName].validate((valid) => {
                  if (valid) {
                      alert('submit!');
                  } else {
                      console.log('error submit!!');
                      return false;
                  }
                  });
              },
              resetForm(formName) {
                  this.$refs[formName].resetFields();
              }
          }
      });
  </script>
  </html>
  ```

## 1.6、表格组件

用于展示多条结构类似的数据，可对数据进行编辑、删除或其他自定义操作。

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>表格组件</title>
      <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
      <script src="js/vue.js"></script>
      <script src="element-ui/lib/index.js"></script>
  </head>
  <body>
      <div id="div">
          <template>
              <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                  prop="date"
                  label="日期"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="name"
                  label="姓名"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="address"
                  label="地址">
                </el-table-column>
  
                <el-table-column
                  label="操作"
                  width="180">
                  <el-button type="warning">编辑</el-button>
                  <el-button type="danger">删除</el-button>
                </el-table-column>
              </el-table>
            </template>
      </div>
  </body>
  <script>
      new Vue({
          el:"#div",
          data:{
              tableData: [{
                  date: '2016-05-02',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1518 弄'
              }, {
                  date: '2016-05-04',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1517 弄'
              }, {
                  date: '2016-05-01',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1519 弄'
              }, {
                  date: '2016-05-03',
                  name: '王小虎',
                  address: '上海市普陀区金沙江路 1516 弄'
              }]
          }
      });
  </script>
  </html>
  ```

## 1.7、顶部导航栏组件

![](.\img\顶部导航栏.png)

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>顶部导航栏</title>
      <link rel="stylesheet" href="../element-ui/lib/theme-chalk/index.css">
      <script src="../js/vue.js"></script>
      <script src="../element-ui/lib/index.js"></script>
  </head>
  <body>
      <div id="div">
          <el-menu
                  :default-active="activeIndex2"
                  class="el-menu-demo"
                  mode="horizontal"
                  @select="handleSelect"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
              <el-menu-item index="1">处理中心</el-menu-item>
              <el-submenu index="2">
                  <template slot="title">我的工作台</template>
                  <el-menu-item index="2-1">选项1</el-menu-item>
                  <el-menu-item index="2-2">选项2</el-menu-item>
                  <el-menu-item index="2-3">选项3</el-menu-item>
                  <el-submenu index="2-4">
                      <template slot="title">选项4</template>
                      <el-menu-item index="2-4-1">选项1</el-menu-item>
                      <el-menu-item index="2-4-2">选项2</el-menu-item>
                      <el-menu-item index="2-4-3">选项3</el-menu-item>
                  </el-submenu>
              </el-submenu>
              <el-menu-item index="3" disabled>消息中心</el-menu-item>
              <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
          </el-menu>
  
      </div>
  </body>
  <script>
      new Vue({
          el:"#div",
          data() {
              return {
                  activeIndex: '1',
                  activeIndex2: '1'
              };
          },
          methods: {
              handleSelect(key, keyPath) {
                  console.log(key, keyPath);
              }
          }
      });
  </script>
  </html>
  ```

## 1.8、侧边导航栏组件

![](.\img\侧边导航栏.png)

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>侧边导航栏</title>
      <link rel="stylesheet" href="../element-ui/lib/theme-chalk/index.css">
      <script src="../js/vue.js"></script>
      <script src="../element-ui/lib/index.js"></script>
  </head>
  <body>
      <div id="div">
        <el-col :span="12">
          <h5>自定义颜色</h5>
          <el-menu
                  default-active="2"
                  class="el-menu-vertical-demo"
                  @open="handleOpen"
                  @close="handleClose"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>导航一</span>
              </template>
              <el-menu-item-group>
                <template slot="title">分组一</template>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="分组2">
                <el-menu-item index="1-3">选项3</el-menu-item>
              </el-menu-item-group>
              <el-submenu index="1-4">
                <template slot="title">选项4</template>
                <el-menu-item index="1-4-1">选项1</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </el-menu-item>
            <el-menu-item index="3" disabled>
              <i class="el-icon-document"></i>
              <span slot="title">导航三</span>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">导航四</span>
            </el-menu-item>
          </el-menu>
        </el-col>
        </el-row>
  
      </div>
  </body>
  <script>
      new Vue({
          el:"#div",
          methods: {
              handleOpen(key, keyPath) {
                  console.log(key, keyPath);
              },
              handleClose(key, keyPath) {
                  console.log(key, keyPath);
              }
          }
      });
  </script>
  </html>
  ```

## 1.9、小结

- Element：网站快速成型工具。是一套为开发者、设计师、产品经理准备的基于Vue的桌面端组件库。
- 使用Element前提必须要有Vue。
- 使用步骤
  1.下载Element核心库。
  2.引入Element样式文件。
  3.引入Vue核心js文件。
  4.引入Element核心js文件。
  5.借助常用组件编写网页。
- 常用组件
  网页基本组成部分，布局、按钮、表格、表单等等~~~
  常用组件不需要记住，只需要在Element官网中复制使用即可。

# 2、界面案例 学生列表

## 2.1、案例效果和分析

![](.\img\综合案例-效果图.png)

## 2.2、头部区域的实现

- **实现思路**

  - 头部效果实现。
  - 侧边栏和主区域效果实现。

- **代码实现**

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>学生列表</title>
      <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
      <script src="js/vue.js"></script>
      <script src="element-ui/lib/index.js"></script>
      <style>
        .el-header{
          background-color: #545c64;
        }
        .header-img{
          width: 100px;
          margin-top: 20px;
        }
      </style>
  </head>
  <body>
    <div id="div">
      <el-container>
        <!-- 头部 -->
        <el-header class="el-header">
          <el-container>
            <div>
              <el-image src="img/export.png" class="header-img"></el-image>
            </div>
            <el-menu
              :default-active="activeIndex2"
              mode="horizontal"
              @select="handleSelect"
              background-color="#545c64"
              text-color="white"
              active-text-color="#ffd04b"
              style="margin-left: auto;">
              <el-menu-item index="1">处理中心</el-menu-item>
              <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
              </el-submenu>
              <el-menu-item index="3"><a href="学生列表.html" target="_self">首页</a></el-menu-item>
            </el-menu>
          </el-container>
        </el-header>
      </el-container>
    </div>
  </body>
  <script>
      new Vue({
          el:"#div"
      });
  </script>
  </html>
  ```

## 2.3、侧边栏区域的实现

```html
<!-- 侧边栏区域 -->
<el-container style="height: 580px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1']">
            <el-submenu index="1">
                <template slot="title"><i class="el-icon-menu"></i>学工部</template>
                <el-menu-item-group>
                    <el-menu-item index="1-1"><i class="el-icon-help"></i>在校学生管理</el-menu-item>
                    <el-menu-item index="1-2"><i class="el-icon-help"></i>学生升级/留级</el-menu-item>
                    <el-menu-item index="1-3"><i class="el-icon-help"></i>学生就业情况</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>咨询部</template>
                <el-menu-item-group>
                    <el-menu-item index="2-1"><i class="el-icon-help"></i>意向学生管理</el-menu-item>
                    <el-menu-item index="2-2"><i class="el-icon-help"></i>未报名学生管理</el-menu-item>
                    <el-menu-item index="2-3"><i class="el-icon-help"></i>已报名学生管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title"><i class="el-icon-menu"></i>教研部</template>
                <el-menu-item-group>
                    <el-menu-item index="3-1"><i class="el-icon-help"></i>已有课程管理</el-menu-item>
                    <el-menu-item index="3-2"><i class="el-icon-help"></i>正在研发课程管理</el-menu-item>
                    <el-menu-item index="3-3"><i class="el-icon-help"></i>新技术课程管理</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
        </el-menu>
    </el-aside>

</el-container>
```

## 2.4、主区域的实现

**主区域和侧边栏区域放在一起**

```html
<!-- 主区域 -->
<el-container>
    <el-main>
        <b style="color: red;font-size: 20px;">学生列表</b>
        <div style="float:right">
            <el-button type="primary">添加学生</el-button>
        </div>
        <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column
                             label="操作"
                             width="180">
                <el-button type="warning">编辑</el-button>
                <el-button type="danger">删除</el-button>
            </el-table-column>
        </el-table>
    </el-main>
</el-container>
```

**在vue中定义data**

```js
<script>
    new Vue({
        el:"#div",
        data:{
          tableData:[
            {
              date:"2088-08-08",
              name:"张三",
              address:"北京市昌平区"
            },{
              date:"2088-08-08",
              name:"李四",
              address:"北京市昌平区"
            },{
              date:"2088-08-08",
              name:"王五",
              address:"北京市昌平区"
            },
          ]
        }
    });
</script>
```

# 3、综合案例 学生管理系统

## 3.1、效果环境的介绍

![](img/%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B-%E7%99%BB%E5%BD%95.png)

![](img/%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B-%E9%A6%96%E9%A1%B5.png)



## 3.2、登录功能的实现

- **环境搭建**

  - 从当天的资料中解压《学生管理系统原始项目》，并导入。

- **代码实现**

  - **html代码**

    ```js
    onSubmit(formName) {
                    // 为表单绑定验证功能
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            //请求服务器完成登录功能
                            axios.post("userServlet","username=" + this.form.username + "&password=" + this.form.password)
                                .then(resp => {
                                    if(resp.data == true) {
                                        //登录成功，跳转到首页
                                        location.href = "index.html";
                                    }else {
                                        //登录失败，跳转到登录页面
                                        alert("登录失败，请检查用户名和密码");
                                        location.href = "login.html";
                                    }
                                })
                        } else {
                            return false;
                        }
                    });
                }
    ```

  - **java代码**

    - **UserServlet.java**

    ```java
    package com.itheima.controller;
    
    import com.itheima.bean.User;
    import com.itheima.service.UserService;
    import com.itheima.service.impl.UserServiceImpl;
    
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.List;
    
    @WebServlet("/userServlet")
    public class UserServlet extends HttpServlet {
        private UserService service = new UserServiceImpl();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //设置请求和响应编码
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
    
            //1.获取请求参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");
    
            //2.封装User对象
            User user = new User(username,password);
    
            //3.调用业务层的登录方法
            List<User> list = service.login(user);
    
            //4.判断是否查询出结果
            if(list.size() != 0) {
                //将用户名存入会话域当中
                req.getSession().setAttribute("username",username);
                //响应给客户端true
                resp.getWriter().write("true");
            }else {
                //响应给客户端false
                resp.getWriter().write("false");
            }
        }
    
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req,resp);
        }
    }
    ```

    - **UserService.java**

    ```java
    package com.itheima.service;
    
    import com.itheima.bean.User;
    import com.itheima.dao.UserMapper;
    import com.itheima.util.SqlSessionUtil;
    import org.apache.ibatis.io.Resources;
    import org.apache.ibatis.session.SqlSession;
    import org.apache.ibatis.session.SqlSessionFactory;
    import org.apache.ibatis.session.SqlSessionFactoryBuilder;
    
    import java.io.IOException;
    import java.io.InputStream;
    import java.util.List;
    
    public class UserService {
    
        public List<User> login(User user) {
            SqlSession sqlSession = SqlSessionUtil.getSession();
    
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> list = mapper.login(user);
            sqlSession.close();
            return list;
        }
    }
    
    ```

    - **UserMapper.java**

    ```java
    package com.itheima.dao;
    
    import com.itheima.bean.User;
    import org.apache.ibatis.annotations.Select;
    
    import java.util.List;
    
    public interface UserMapper {
        /*
            登录方法
         */
        @Select("SELECT * FROM user WHERE username=#{username} AND password=#{password}")
        public abstract List<User> login(User user);
    }
    
    ```

## 3.3、分页查询功能的实现

```markdown
#分析:
1. index.html 侧边栏(在校学生管理选项) 
	点击 调用 findAll 函数  -> 修改数据 -> 视图更新(stuList.html)

2. vue生命周期
	created : (data加载好,视图还没好)
		调用了 selectByPage函数
```



- **代码实现**

  - **html代码**

    ```html
    <!--
            分页组件
              @size-change： 当改变每页条数时触发的函数
              @current-change：当改变页码时触发的函数
              current-page ：默认的页码
              :page-sizes：每页条数选择框中显示的值
              :page-size : 默认的每页条数
              layout： 分页组件的布局
                  total（总条数）, sizes(每页条数), prev（上一页）, pager(所有的页码), next(下一页), jumper（跳转页码）
              :total: 总条数
        -->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pagination.currentPage"
                :page-sizes="[3,5,8]"
                :page-size="pagination.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="pagination.total">
        </el-pagination>
    ```



    ```js
    <script>
        new Vue({
            el:"#div",
            data:{
                dialogTableVisible4add: false,  //添加窗口显示状态
                dialogTableVisible4edit: false, //编辑窗口显示状态
                formData:{},//添加表单的数据
                editFormData: {},//编辑表单的数据
                tableData:[],//表格数据
                pagination: {
                    currentPage: 1, //当前页
                    pageSize: 5,    //每页显示条数
                    total: 0        //总条数
                },
                rules: {
                    number: [
                        {required: true, message: '请输入学号', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    birthday: [
                        {required: true, message: '请选择日期', trigger: 'change'}
                    ],
                    address: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                        {min: 2, max: 200, message: '长度在 2 到 200 个字符', trigger: 'blur'}
                    ],
                }
            },
            methods:{
                //分页查询功能
                selectByPage(){
                    axios.post("studentServlet","method=selectByPage&currentPage=" + this.pagination.currentPage + "&pageSize=" + this.pagination.pageSize)
                        .then(resp => {
                             // console.log(resp.data);
                            //将查询出的数据赋值tableData
                            this.tableData = resp.data.list;
                            //设置分页参数
                            //当前页
                            this.pagination.currentPage = resp.data.page;
                            //总条数
                            this.pagination.total = resp.data.sum;
                        })
                },
                //改变每页条数时执行的函数
                handleSizeChange(pageSize) {
                    //修改分页查询的参数
                    this.pagination.pageSize = pageSize;
                    //重新执行查询
                    this.selectByPage();
                },
                //改变页码时执行的函数
                handleCurrentChange(pageNum) {
                    //修改分页查询的参数
                    this.pagination.currentPage = pageNum;
                    //重新执行查询
                    this.selectByPage();
                },
                showAddStu() {
                    //弹出窗口
                    this.dialogTableVisible4add = true;
                },
                resetForm(addForm) {
                    //双向绑定，输入的数据都赋值给了formData， 清空formData数据
                    this.formData = {};
                    //清除表单的校验数据
                    this.$refs[addForm].resetFields();
                },
                showEditStu(row) {
                    //1. 弹出窗口
                    this.dialogTableVisible4edit = true;
    
                    //2. 显示表单数据
                    this.editFormData = {
                        number:row.number,
                        name:row.name,
                        birthday:row.birthday,
                        address:row.address,
                    }
                }   
            },
            created(){
                //调用分页查询功能
                this.selectByPage();
            }
        });
    </script>
    ```

  - **java代码**

    - **1、创建StudentServlet.java**

    ```java
    package com.itheima.controller;
    
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.github.pagehelper.Page;
    import com.github.pagehelper.PageInfo;
    import com.itheima.bean.Student;
    import com.itheima.service.StudentService;
    import com.itheima.service.impl.StudentServiceImpl;
    import org.apache.commons.beanutils.BeanUtils;
    import org.apache.commons.beanutils.ConvertUtils;
    import org.apache.commons.beanutils.Converter;
    
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Map;
    
    @WebServlet("/studentServlet")
    public class StudentServlet extends HttpServlet {
        private StudentService service = new StudentServiceImpl();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //设置请求和响应编码
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
    
            //1.获取方法名
            String method = req.getParameter("method");
            if(method.equals("selectByPage")) {
                //分页查询功能
                selectByPage(req,resp);
            }
        }
    
        /*
            分页查询功能
         */
        private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
            //获取请求参数
            String currentPage = req.getParameter("currentPage");
            String pageSize = req.getParameter("pageSize");
    
            //调用业务层的查询方法
            PageBean page = service.selectByPage(currentPage, pageSize);
    
            //将PageBean转成json，响应给客户端
            try {
                String json = new ObjectMapper().writeValueAsString(page);
                resp.getWriter().write(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req,resp);
        }
    }
    ```

    - **2、创建StudentService.java**

    ```java
    /*
        学生业务层实现类
     */
    public class StudentService {
    
        /*
            分页查询功能
         */
        public PageBean selectByPage(String pageStr, String countStr) {
            //1. 请求参数 处理
            int page = 1; // 当前页码( 默认初始值)
            int count = 5; // 每页查询最大数量
    
            // 代码健壮性考虑: 防止前端不传参发生的错误
            if(pageStr != null && pageStr.length() > 0){
                page = Integer.parseInt(pageStr);
            }
    
            if(countStr != null && countStr.length() > 0){
                count = Integer.parseInt(countStr);
            }
            //2. 查询数据库
            int index = (page -1) * count; // 分页查询初始索引
    
            SqlSession session = SqlSessionUtil.getSession();//自动commit
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            // 查询每页显示的联系人数据 (加条件)
            List<Student> list = mapper.findStudentByPage(index,count);
            // 查询联系人总条数(加条件)
            int sum = mapper.findStudentSum();
    
            session.close();
            //3. 封装PageBean
            PageBean bean = PageBean.getBean(page, count, list, sum);
    
            return bean;
        }
    }
    ```

    - **3、创建StudentMapper.java**

    ```java
    
    /*
        学生持久层接口
     */
    public interface StudentMapper {
        /*
            分页查询
        * */
        @Select("SELECT * FROM student LIMIT #{index},#{count}")
        List<Student> findStudentByPage(@Param("index") int index, @Param("count")int count);
        /*
        *   查询总数
        * */
        @Select("SELECT count(*) FROM student")
        int findStudentSum();
    }
    
    ```

## 3.4、添加功能的实现

```markdown
1. 添加按钮
		 <el-button  @click="showAddStu">添加学生</el-button>
2. showAddStu
		dialogTableVisible4add: false  -> true

3.  添加用户弹出框 
	:visible.sync 控制弹出框是否显示的 (true显示,false隐藏)
<el-dialog title="添加学生信息" :visible.sync="dialogTableVisible4add" @close="resetForm('addForm')">

4. 添加按钮
	   <el-button type="primary" @click="addStu()">添加</el-button>
```



- 代码实现**

  - **html代码**

    在stuList.html中增加“添加功能”代码

    ```js
    //添加学生功能
                addStu(){
                    let param = "method=addStu&number=" + this.formData.number + "&name=" + this.formData.name +
                            "&birthday=" + this.formData.birthday + "&address=" + this.formData.address +
                            "&currentPage=" + this.pagination.currentPage + "&pageSize=" + this.pagination.pageSize;
                    axios.post("studentServlet",param)
                        .then(resp => {
                             // console.log(resp.data);
                            //将查询出的数据赋值tableData
                            this.tableData = resp.data.list;
                            //设置分页参数
                            //当前页
                            this.pagination.currentPage = resp.data.page;
                            //总条数
                            this.pagination.total = resp.data.sum;
                        })
                    //关闭添加窗口
                    this.dialogTableVisible4add = false;
                }
    ```

  - **java代码**

    - 1、在StudentServlet.java中增加“添加功能”代码-addStu

    ```java
    	/*
    	*1、直接复制会报错
    	*2、需要将此行代码需要添加到“doGet”方法中
    	*3、增加“addStu”方法名的判断	
        */
    	else if(method.equals("addStu")) {
                //添加数据功能
                addStu(req,resp);
         }	
    ==================================================================================
    
    	/*
            添加数据功能
         */
        private void addStu(HttpServletRequest req, HttpServletResponse resp) {
            //获取请求参数
            Map<String, String[]> map = req.getParameterMap();
            String currentPage = req.getParameter("currentPage");
            String pageSize = req.getParameter("pageSize");
    
            //封装Student对象
            Student stu = new Student();
    
            try {
                BeanUtils.populate(stu,map);
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            //调用业务层的添加方法
            service.addStu(stu);
    
            //重定向到分页查询功能
            try {
                resp.sendRedirect("studentServlet?method=selectByPage&currentPage=" + currentPage + "&pageSize=" + pageSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    ```

    - 2、在StudentService.java中增加“添加功能”-addStu

    ```java
     /*
            添加数据方法
         */
        public void addStu(Student stu) {
            SqlSession sqlSession = SqlSessionUtil.getSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.addStu(stu);
            sqlSession.close();
        }
    ```

    - 4、StudentMapper.java中增加“添加功能”-addStu

    ```java
        /*
            添加数据方法
         */
        @Insert("INSERT INTO student VALUES (#{number},#{name},#{birthday},#{address})")
        public abstract void addStu(Student stu);
    ```

## 3.5、修改功能的实现

```markdown
# 分析
1. 编辑按钮
	<template slot-scope="props">
              <el-button type="warning" @click="showEditStu(props.row)">编辑</el-button>
              
     当这个按钮被点击的事件,触发  showEditStu 函数 , 参数 props.row 表示的该行的数据对象
    {number : "hm001" , name : "张三" , birthday : "1995-01-01",address : "杭州"}
```



- **代码实现**

  - **html代码**

    在stuList.html中增加“修改功能”代码

    ```js
    //修改数据功能
                updateStu() {
                    let param = "method=updateStu&number=" + this.editFormData.number + "&name=" + this.editFormData.name +
                        "&birthday=" + this.editFormData.birthday + "&address=" + this.editFormData.address +
                        "&currentPage=" + this.pagination.currentPage + "&pageSize=" + this.pagination.pageSize;
                    axios.post("studentServlet",param)
                        .then(resp => {
                             // console.log(resp.data);
                            //将查询出的数据赋值tableData
                            this.tableData = resp.data.list;
                            //设置分页参数
                            //当前页
                            this.pagination.currentPage = resp.data.page;
                            //总条数
                            this.pagination.total = resp.data.sum;
                        })
                    //关闭编辑窗口
                    this.dialogTableVisible4edit = false;
                }
    ```

  - **java代码**

    - 1、在StudentServlet.java中增加“修改功能”-updateStu

    ```java
    	/*
    	*1、直接复制会报错
    	*2、需要将此行代码需要添加到“doGet”方法中
    	*3、增加“updateStu”方法名的判断	
        */
    	else if(method.equals("updateStu")) {
                //添加数据功能
                updateStu(req,resp);
         }	
    ================================================================================== 
    /*
            修改数据功能
         */
        private void updateStu(HttpServletRequest req, HttpServletResponse resp) {
            //获取请求参数
            Map<String, String[]> map = req.getParameterMap();
            String currentPage = req.getParameter("currentPage");
            String pageSize = req.getParameter("pageSize");
    
            //封装Student对象
            Student stu = new Student();
    
            try {
                BeanUtils.populate(stu,map);
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            //调用业务层的修改方法
            service.updateStu(stu);
    
            //重定向到分页查询功能
            try {
                resp.sendRedirect("studentServlet?method=selectByPage&currentPage=" + currentPage + "&pageSize=" + pageSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    ```

    - 2、在StudentService.java中增加“修改功能”-updateStu

    ```java
    
     /*
            修改数据方法
         */
        public void updateStu(Student stu) {
            SqlSession sqlSession = SqlSessionUtil.getSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.updateStu(stu);
            sqlSession.close();
        }
    ```

    - 3、StudentMapper.java中增加“修改功能”-updateStu

    ```java
    /*
            修改数据方法
         */
        @Update("UPDATE student SET name=#{name},birthday=#{birthday},address=#{address} WHERE number=#{number}")
        public abstract void updateStu(Student stu);
    ```

## 3.6、删除功能的实现

- **代码实现**

  - **html代码**

    在stuList.html中增加“删除功能”代码

    ```js
    //删除数据功能
                deleteStu(row) {
                    if(confirm("确定要删除" + row.number + "数据?")) {
                        let param = "method=deleteStu&number=" + row.number +
                            "&currentPage=" + this.pagination.currentPage + "&pageSize=" + this.pagination.pageSize;
                        axios.post("studentServlet",param)
                            .then(resp => {
                                 // console.log(resp.data);
                                //将查询出的数据赋值tableData
                                this.tableData = resp.data.list;
                                //设置分页参数
                                //当前页
                                this.pagination.currentPage = resp.data.page;
                                //总条数
                                this.pagination.total = resp.data.sum;
                            })
                    }
                }
    ```

  - **java代码**

    - 1、在StudentServlet.java中增加“删除功能”-

    ```java
    	/*
    	*1、直接复制会报错
    	*2、需要将此行代码需要添加到“doGet”方法中
    	*3、增加“deleteStu”方法名的判断	
        */
    	else if(method.equals("deleteStu")) {
                //添加数据功能
                deleteStu(req,resp);
         }	
    ==================================================================================
    
    
     /*
            删除数据功能
         */
        private void deleteStu(HttpServletRequest req, HttpServletResponse resp) {
            //获取请求参数
            String number = req.getParameter("number");
            String currentPage = req.getParameter("currentPage");
            String pageSize = req.getParameter("pageSize");
    
            //调用业务层的删除方法
            service.deleteStu(number);
    
            //重定向到分页查询功能
            try {
                resp.sendRedirect("studentServlet?method=selectByPage&currentPage=" + currentPage + "&pageSize=" + pageSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    ```

    - 2、在StudentService.java中增加“删除功能”-

    ```java
    /*
            删除数据方法
         */
        public void deleteStu(String number) {
            SqlSession sqlSession = SqlSessionUtil.getSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.deleteStu(number);
            sqlSession.close();
        }
    ```

    - 4、StudentMapper.java中增加“删除功能”-

    ```java
        /*
            删除数据方法
         */
        @Delete("DELETE FROM student WHERE number=#{number}")
        public abstract void deleteStu(String number);
    ```

 