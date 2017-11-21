<script type="text/x-template" id="mainNavApp">
    <div v-cloak>
        <header class="main-header">
            <!-- Logo -->
            <a href="${rc.contextPath}" class="logo">
                <!-- mini logo for sidebar mini 50x50 pixels -->
                <span class="logo-mini"><b>CARP</b>V1.0</span>
                <!-- logo for regular state and mobile devices -->
                <span class="logo-lg"><b>CARP</b>V1.0</span>
            </a>

            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top">
                <!-- Sidebar toggle button-->
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                <ul class="nav navbar-nav">
                    <li :class="(firstLevelActiveId==menu.id)?'active':''" v-for="(menu,index) in menus">
                        <a href="#" @click="firstLevelActiveId = menu.id">{{menu.name}}</a>
                        <span class="sr-only" v-if="firstLevelActiveId==menu.id">(current)</span>
                    </li>
                </ul>

                <!-- Navbar Right Menu -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <!-- Messages: style can be found in dropdown.less-->
                        <li class="dropdown messages-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-envelope-o"></i>
                                <span class="label label-success">4</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have 4 messages</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li><!-- start message -->
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/avatar2.png"
                                                         class="img-circle" alt="User Image">
                                                </div>
                                                <h4>
                                                    Support Team
                                                    <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                        <!-- end message -->
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/user3-128x128.jpg"
                                                         class="img-circle" alt="User Image">
                                                </div>
                                                <h4>
                                                    AdminLTE Design Team
                                                    <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/user4-128x128.jpg"
                                                         class="img-circle" alt="User Image">
                                                </div>
                                                <h4>
                                                    Developers
                                                    <small><i class="fa fa-clock-o"></i> Today</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/user3-128x128.jpg"
                                                         class="img-circle" alt="User Image">
                                                </div>
                                                <h4>
                                                    Sales Department
                                                    <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/user4-128x128.jpg"
                                                         class="img-circle" alt="User Image">
                                                </div>
                                                <h4>
                                                    Reviewers
                                                    <small><i class="fa fa-clock-o"></i> 2 days</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="footer"><a href="#">See All Messages</a></li>
                            </ul>
                        </li>
                        <!-- Notifications: style can be found in dropdown.less -->
                        <li class="dropdown notifications-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-bell-o"></i>
                                <span class="label label-warning">10</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have 10 notifications</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-warning text-yellow"></i> Very long description here
                                                that
                                                may not fit into the
                                                page and may cause design problems
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-users text-red"></i> 5 new members joined
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-user text-red"></i> You changed your username
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="footer"><a href="#">View all</a></li>
                            </ul>
                        </li>
                        <!-- Tasks: style can be found in dropdown.less -->
                        <li class="dropdown tasks-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-flag-o"></i>
                                <span class="label label-danger">9</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have 9 tasks</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Design some buttons
                                                    <small class="pull-right">20%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-aqua" style="width: 20%"
                                                         role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                         aria-valuemax="100">
                                                        <span class="sr-only">20% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <!-- end task item -->
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Create a nice theme
                                                    <small class="pull-right">40%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-green" style="width: 40%"
                                                         role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                         aria-valuemax="100">
                                                        <span class="sr-only">40% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <!-- end task item -->
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Some task I need to do
                                                    <small class="pull-right">60%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-red" style="width: 60%"
                                                         role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                         aria-valuemax="100">
                                                        <span class="sr-only">60% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <!-- end task item -->
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Make beautiful transitions
                                                    <small class="pull-right">80%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-yellow" style="width: 80%"
                                                         role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                         aria-valuemax="100">
                                                        <span class="sr-only">80% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <!-- end task item -->
                                    </ul>
                                </li>
                                <li class="footer">
                                    <a href="#">View all tasks</a>
                                </li>
                            </ul>
                        </li>
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/avatar2.png" class="user-image"
                                     alt="User Image">
                                <span class="hidden-xs"><#include "../layout/widget/currentUserName.ftl"/></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header">
                                    <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/avatar2.png"
                                         class="img-circle"
                                         alt="User Image">

                                    <p>
                                    <#include "../layout/widget/currentUserName.ftl"/>- Web Developer
                                        <small>Member since Nov. 2012</small>
                                    </p>
                                </li>
                                <!-- Menu Body -->
                                <li class="user-body">
                                    <div class="row">
                                        <div class="col-xs-4 text-center">
                                            <a href="#">Followers</a>
                                        </div>
                                        <div class="col-xs-4 text-center">
                                            <a href="#">Sales</a>
                                        </div>
                                        <div class="col-xs-4 text-center">
                                            <a href="#">Friends</a>
                                        </div>
                                    </div>
                                    <!-- /.row -->
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="${rc.contextPath}/logout" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <!-- Control Sidebar Toggle Button -->
                        <li>
                            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                        </li>
                    </ul>
                </div>

            </nav>
        </header>
        <!-- Left side column. contains the logo and sidebar -->
        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="${rc.contextPath}/webjars/AdminLTE/dist/img/avatar2.png" class="img-circle"
                             alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p><#include "../layout/widget/currentUserName.ftl"/></p>
                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>
                <!-- search form -->
                <form action="#" method="get" class="sidebar-form">
                    <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
                    </div>
                </form>
                <!-- /.search form -->
                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li class="header"></li>
                    <li :class="leftMenuActiveClass(menu)" v-for="(menu,index) in leftMenus">
                        <a href="#" @click="leftMenuActiveId = menu.id">
                            <i :class="leftMenuClass(index)"></i>
                            <span>{{menu.name}}</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li :class="{'active': sub.id == subMenuActiveId}" v-for="sub in menu.children">
                                <a :href="pageUrl(sub)">
                                    <i class="fa fa-circle-o"></i> {{sub.name}}
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
    </div>
</script>
<script type="text/javascript">
    var globalMainNav = {
        template: '#mainNavApp',
        data: function () {
            return {
                menus: [],
                //一级菜单
                firstLevelActiveId: -1,
                //二级菜单
                leftMenuActiveId:-1,
                //三级菜单
                subMenuActiveId:-1,
                leftMenus:[],
                menuIcons: ['fa-dashboard', 'fa-files-o', 'fa-th', 'fa-pie-chart',
                    'fa-laptop', 'fa-edit', 'fa-table', 'fa-calendar'
                    , 'fa-folder', 'fa-share', 'fa-book']
            }
        },
        watch:{
            firstLevelActiveId:function (val,oldVal) {
                var self = this;
                self.menus.forEach(function (menu) {
                    if(menu.id == val){
                        self.leftMenus = menu.children;
                    }
                })
            }
        },
        methods: {
            leftMenuClass:function (index) {
                var self = this;
                return 'fa '+self.menuIcons[index%self.menuIcons.length];
            },
            leftMenuActiveClass:function (menu) {
                var self = this;
                if(menu.id == self.leftMenuActiveId){
                    return 'active treeview';
                }
            return 'treeview';
            },
            pageUrl:function (subMenu) {
                var self = this;
                let baseUrl ='${rc.contextPath}';
                let url  = subMenu.url;
                if(!url.startsWith("/")){
                    url = '/'+url;
                }
                return baseUrl +url
                        + "?f="+self.firstLevelActiveId
                        +'&s='+self.leftMenuActiveId
                        +'&t='+subMenu.id;
            }
        },
        created: function () {
            let self = this;
            <#if RequestParameters.f??>
                self.firstLevelActiveId=${RequestParameters.f};
            </#if>
        <#if RequestParameters.s??>
            self.leftMenuActiveId=${RequestParameters.s};
        </#if>
        <#if RequestParameters.t??>
            self.subMenuActiveId=${RequestParameters.t};
        </#if>
            axios.get("${rc.contextPath}/index/menus")
                    .then(response => {
                        self.menus = response.data.payload;
                        if(self.firstLevelActiveId == -1){
                            self.firstLevelActiveId = self.menus[0].id;
                        }else{
                            self.menus.forEach(function (menu) {
                                if(menu.id == self.firstLevelActiveId){
                                    self.leftMenus = menu.children;
                                }
                            })
                        }
                        if(self.leftMenuActiveId == -1){
                            self.leftMenus = self.menus[0].children;
                        }
                    });
        }
    };
</script>