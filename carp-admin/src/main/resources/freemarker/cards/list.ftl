<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>
        <el-button class="filter-item" style="margin-left: 10px;" @click="add" type="primary" icon="edit">添加</el-button>
        <el-button type="primary" icon="delete" @click="deleteSelected" :disabled="shouldDisableDelete"></el-button>
        <el-collapse accordion>
            <el-collapse-item title="More...">
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
        <el-table :data.sync="cards" style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="expand">
                <template scope="props">
                    <p>卡号: {{ props.row.cardId }}</p>
                    <p>余额: {{ props.row.balanceValue }}</p>
                    <p>冻结余额: {{ props.row.frozenValue }}</p>
                    <p>可用余额: {{ props.row.balanceValue - props.row.frozenValue }}</p>
                </template>
            </el-table-column>
            <el-table-column label="卡号" prop="cardId"></el-table-column>
            <el-table-column label="发行面值" prop="issueValue"></el-table-column>
            <el-table-column label="冻结金额" prop="frozenValue"></el-table-column>
            <el-table-column label="余额" prop="balanceValue"></el-table-column>

            <el-table-column align="center" label="操作">
                <template scope="scope">
                    <el-button size="small" type="success" @click="edit(scope.row)">编辑
                    </el-button>
                    <el-button size="small" type="danger" @click="deleteRow(row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="query.page" :page-sizes="[5, 10, 20, 40]" :page-size="query.size"
                       layout="total, sizes, prev, pager, next, jumper" :total="totalElements">
        </el-pagination>

        <el-dialog title="新增卡片信息 " :visible.sync="createVisible">
            <el-form>
                <el-form-item label="发行面值">
                    <el-input v-model="cardItem.issueValue" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="createVisible = false">取 消</el-button>
                <el-button type="primary" @click="createCard">保 存</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修改卡片信息 " :visible.sync="editVisible">
            <el-form>
                <el-form-item label="卡号" readonly="true">
                    <el-input v-model="cardItem.cardId" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="发行面值" readonly="true">
                    <el-input v-model="cardItem.issueValue" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="冻结金额">
                    <el-input v-model="cardItem.frozenValue" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="余额">
                    <el-input v-model="cardItem.balanceValue" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateCard">保 存</el-button>
            </div>
        </el-dialog>
    </div>
</@layout.main>
<#macro myPageJS>
    <script>
        new Vue({
            el: '#app',
            computed: {
                shouldDisableDelete() {
                    if (this.selectedCards.length > 0) {
                        return false;
                    }
                    return true;
                }
            },
            methods: {
                add(){
                    var self = this;
                    self.cardItem = {};
                    self.createVisible = true;
                },
                edit(row){
                    var self = this;
                    var item = row;
                    self.cardItem = JSON.parse(JSON.stringify(item));
                    self.editVisible = true;
                },
                createCard(){
                    var self = this;
                    axios.post("${rc.contextPath}/cards", JSON.parse(JSON.stringify(self.cardItem))).then(response => {
                        if(response.data.procCode != 200){
                            this.$message({
                                type: 'success',
                                message:  response.data.message
                            });
                            return;
                        }
                        self.createVisible = false;
                        // save server side
                        this.$message({
                            type: 'success',
                            message: '保存成功！'
                        });
                    }).catch(error =>{
                        console.log(error);
                    });
                    self.queryCards();
                },
                updateCard(){
                    var self = this;
                    axios.put("${rc.contextPath}/cards", JSON.parse(JSON.stringify(self.cardItem))).then(response => {
                        console.log(response);
                        self.editVisible = false;
                        // save server side
                        this.$message({
                            type: 'success',
                            message: '保存成功！'
                        });
                    })
                    self.queryCards();
                },

                deleteRow(row){
                    this.deleteBySelectedCards(row);
                },
                deleteSelected(){
                    this.deleteBySelectedCards(this.selectedCards);
                },
                deleteBySelectedCards(cards){
                    var self = this;
                    this.$confirm('永久删除选中行?', '确认删除', {
                        confirmButtonText: '确认删除',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        var ids = [];
                        cards.forEach(function (val) {
                            ids.push(val.id);
                            self.cards.splice(self.cards.indexOf(val), 1);
                        });
                        self.deleteCardsByIds(ids);
                        this.$message({
                            type: 'success',
                            message: '删除成功！'
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消删除！'
                        });
                    });
                },
                deleteCardsByIds(ids){
                    axios.post("${rc.contextPath}/cards/delete", ids).then(response => {
                        console.log(response);
                    });
                },
                handleSizeChange(newSize) {
                    this.query.size = newSize;
                    this.queryCards();
                },
                handleCurrentChange(newPage) {
                    this.query.page = newPage;
                    this.queryCards();
                },
                handleSelectionChange(val) {
                    this.selectedCards = val;
                },
                queryCards(){
                    axios.get("${rc.contextPath}/cards/data", {params: this.query}).then(response => {
                        console.log(response);
                        this.cards = response.data.payload.content;
                        this.totalPage = response.data.payload.totalPages;
                        this.totalElements = response.data.payload.totalElements;
                    })
                }
            },
            created(){
                this.queryCards();
            },
            data: function () {
                return {
                    query: {
                        page: 1,
                        size: 10
                    },
                    totalPage: 0,
                    totalElements: 0,
                    cards: [],
                    selectedCards: [],
                    createVisible: false,
                    editVisible: false,
                    cardItem: {}
                }
            }
        })
    </script>
</section>
</#macro>