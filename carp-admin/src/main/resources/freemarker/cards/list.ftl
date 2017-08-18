<@layout.main pageJS=myPageJS>
cards list page. vue & element UI test.

<div id="app">
    <el-button type="primary">新增</el-button>
    <el-button type="primary" icon="edit"></el-button>
    <el-button type="primary" icon="delete"></el-button>
    <el-table
            :data="cards"
            style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column
                type="selection"
                width="55">
        </el-table-column>
        <el-table-column type="expand">
            <template scope="props">
                <p>卡号: {{ props.row.cardId }}</p>
                <p>余额: {{ props.row.balanceValue }}</p>
                <p>冻结余额: {{ props.row.frozenValue }}</p>
                <p>可用余额: {{ props.row.balanceValue - props.row.frozenValue }}</p>

            </template>
        </el-table-column>
        <el-table-column
                label="卡号"
                prop="cardId">
        </el-table-column>
        <el-table-column
                label="发行面值"
                prop="issueValue">
        </el-table-column>
        <el-table-column
                label="冻结金额"
                prop="frozenValue">
        </el-table-column>
        <el-table-column
                label="余额"
                prop="balanceValue">
        </el-table-column>
    </el-table>
    <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="query.page"
            :page-sizes="[1, 10, 20, 40]"
            :page-size="query.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>
</div>
</@layout.main>
<#macro myPageJS>
<script>
    new Vue({
        el: '#app',
        methods: {
            handleSizeChange(newSize) {
                this.query.size = newSize;
                this.queryCards();
            },
            handleCurrentChange(newPage) {
               this.query.page = newPage;
                this.queryCards();
            },
            handleSelectionChange(val) {
                console.log(`select page: ` + val);
            },
            queryCards(){
                axios.get("${rc.contextPath}/cards/data",{params:this.query}).then(response =>{
                    console.log(response);
                    this.cards = response.data.payload.content;
                    this.total = response.data.payload.totalPages;
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
                total:1,
                cards: null
            }
        }
    })
</script>
</#macro>