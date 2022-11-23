<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>
        <el-collapse accordion>
            <el-collapse-item title="更多功能...">
                <div class="filter-container">
                    <el-input style="width: 200px;" class="filter-item" placeholder="关键字" v-model="query.keyword" clearable>
                    </el-input>
                    <el-button class="filter-item" type="primary" icon="search" @click="queryItems">搜索</el-button>
                    <el-button class="filter-item" type="primary" icon="document" @click="">导出</el-button>

                </div>
            </el-collapse-item>
        </el-collapse>
        <el-table :data.sync="items" style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="expand">
                <template scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="id">
                            <span> {{ props.row.id }}</span>
                        </el-form-item>
                        <el-form-item label="uuid">
                            <span> {{ props.row.uuid }}</span>
                        </el-form-item>
                        <el-form-item label="用户名">
                            <span> {{ props.row.userName }}</span>
                        </el-form-item>
                        <el-form-item label="电话">
                            <span> {{ props.row.mobile }}</span>
                        </el-form-item>
                        <el-form-item label="昵称">
                            <span> {{ props.row.nickName }}</span>
                        </el-form-item>
                        <el-form-item label="性别">
                            <span> {{ props.row.gender }}</span>
                        </el-form-item>
                        <el-form-item label="头像">
                            <span> {{ props.row.avatar }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="昵称" prop="nickName"></el-table-column>
            <el-table-column label="用户名" prop="userName"></el-table-column>
            <el-table-column label="电话" prop="mobile"></el-table-column>
            <el-table-column align="center" label="操作">
                <template scope="scope">
                    <el-button size="small" type="warning"
                               @click="changePasswordModel={password:'',name:scope.row.userName};showChangePassword=true">改密
                    </el-button>
                    <el-button size="small" type="danger" @click="">禁用
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="query.page" :page-sizes="[5, 10, 20, 40]" :page-size="query.size"
                       layout="total, sizes, prev, pager, next, jumper" :total="totalElements">
        </el-pagination>


        <el-dialog title="修改密码" :visible.sync="showChangePassword">
            <el-form label-width="90px" ref="changePasswordForm" :rules="changePasswordFormRules" :model="changePasswordModel">
                <el-form-item label="新密码" prop="password">
                    <el-input v-model="changePasswordModel.password" placeholder="请输入新密码" type="password"></el-input>
                </el-form-item>
                <el-button @click="showChangePassword=false">取消</el-button>
                <el-button @click="handleChangePassword"  type="primary">保存</el-button>
            </el-form>
        </el-dialog>
</section>
</@layout.main>
<#macro myPageJS>
<script>
    new Vue({
        el: '#app',
        computed: {
        },
        methods: {
            handleChangePassword(){
                let self =this;
                self.$refs.changePasswordForm.validate(
                        (valid) =>{
                            if(valid){
                                axios.put("${rc.contextPath}/customer/"+self.changePasswordModel.name+"/password",
                                        {password:self.changePasswordModel.password})
                                        .then(response=>{
                                            self.showChangePassword=false;
                                            self.$message({
                                                type: 'success',
                                                message: "修改密码成功!"
                                            });
                                            self.queryItems();
                                        });
                            }
                        }
                );
            },
            handleSizeChange(newSize) {
                this.query.size = newSize;
                this.queryItems();
            },
            handleCurrentChange(newPage) {
                this.query.page = newPage;
                this.queryItems();
            },
            handleSelectionChange(val) {
                this.selectedItems = val;
            },
            queryItems(){
                axios.get("${rc.contextPath}/customer/data", {params: this.query}).then(response => {
                    this.items = response.data.payload.content;
                    this.totalPage = response.data.payload.totalPages;
                    this.totalElements = response.data.payload.totalElements;
                })
            }
        },
        created(){
            this.queryItems();

        },
        data: function () {
            return {
                query: {
                    page: 1,
                    size: 10,
                    keyword:''
                },
                totalPage: 0,
                totalElements: 0,
                items: [],
                changePasswordModel:{},
                showChangePassword:false,
                changePasswordFormRules:{
                    password:[
                        {type:"string",required:true,message:"请输入新密码",trigger:"blur"},
                        {type:"string",min:6,max:20,message:"密码不符合规则",trigger:"blur"}
                    ]
                }
            }
        }
    })
</script>
</#macro>