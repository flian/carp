<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app">
        <el-button type="primary">新增</el-button>
        <el-button type="primary" icon="edit" :disable="!(selectedCards && selectedCards.length == 1) "></el-button>
        <el-button type="primary" icon="delete" @click="deleteSelected" :disabled="!(selectedCards && selectedCards.length > 0) "></el-button>
        <el-table :data.sync="cards" style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection"  width="55"></el-table-column>
            <el-table-column type="expand">
                <template scope="props">
                    <p>卡号: {{ props.row.cardId }}</p>
                    <p>余额: {{ props.row.balanceValue }}</p>
                    <p>冻结余额: {{ props.row.frozenValue }}</p>
                    <p>可用余额: {{ props.row.balanceValue - props.row.frozenValue }}</p>
                </template>
            </el-table-column>
            <el-table-column label="卡号"  prop="cardId"></el-table-column>
            <el-table-column label="发行面值" prop="issueValue"></el-table-column>
            <el-table-column label="冻结金额" prop="frozenValue"></el-table-column>
            <el-table-column label="余额" prop="balanceValue"></el-table-column>
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
            methods: {
                deleteSelected(){
                    var self = this;
                    this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
                        confirmButtonText: 'OK',
                        cancelButtonText: 'Cancel',
                        type: 'warning'
                    }).then(() => {
                        var ids = [];
                        self.selectedCards.forEach(function(val,idx){
                            ids.push(val.id);
                            self.cards.splice(self.cards.indexOf(val),1);
                        });
                        //todo delete card from server side
                        this.$message({
                            type: 'success',
                            message: 'Delete completed'
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: 'Delete canceled'
                        });
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
                    totalElements:0,
                    cards: null,
                    selectedCards: null
                }
            }
        })
    </script>
</section>
</#macro>