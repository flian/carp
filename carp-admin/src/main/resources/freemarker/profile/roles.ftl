<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>
        <el-button class="filter-item" style="margin-left: 10px;" @click="" type="primary" icon="edit">添加</el-button>

        <el-collapse accordion>
            <el-collapse-item title="更多功能...">
                <div class="filter-container">
                    <el-input style="width: 200px;" class="filter-item" placeholder="标题">
                    </el-input>
                    <el-input style="width: 200px;" class="filter-item" placeholder="姓名">
                    </el-input>
                    <el-button class="filter-item" type="primary" icon="search" @click="">搜索</el-button>
                    <el-button class="filter-item" type="primary" icon="document" @click="">导出</el-button>
                    <el-checkbox class="filter-item">显示审核人</el-checkbox>
                </div>
            </el-collapse-item>
        </el-collapse>
        <el-table :data.sync="items" style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="expand">
                <template scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="ID">
                            <span> {{ props.row.id }}</span>
                        </el-form-item>
                        <el-form-item label="编码">
                            <span> {{ props.row.code }}</span>
                        </el-form-item>
                        <el-form-item label="名称">
                            <span> {{ props.row.name }}</span>
                        </el-form-item>
                        <el-form-item label="授予用户">
                            <span> {{ props.row.users }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="ID" prop="id"></el-table-column>
            <el-table-column label="编码" prop="code"></el-table-column>
            <el-table-column label="名称" prop="name"></el-table-column>


            <el-table-column align="center" label="操作" >
                <template scope="scope">
                    <el-button size="small" type="success" @click="">分配角色
                    </el-button>
                    <el-button size="small" type="warning" @click="">改密
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
    </div>
</@layout.main>
<#macro myPageJS>
    <script>
        new Vue({
            el: '#app',
            computed: {
            },
            methods: {
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
                    axios.get("${rc.contextPath}/roles/data", {params: this.query}).then(response => {
                        console.log(response);
                        this.items = response.data.content;
                        this.totalPage = response.data.totalPages;
                        this.totalElements = response.data.totalElements;
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
                    selectedItems:[]
                }
            }
        })
    </script>
</section>
</#macro>