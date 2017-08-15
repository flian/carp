<@layout.main pageJS=myPageJS>
    cards list page. vue & element UI test.

<div id="app">
    <el-button type="primary">新增</el-button>
    <el-button type="primary" icon="edit"></el-button>
    <el-button type="primary" icon="delete"></el-button>
    <el-table
            :data="tableData3"
            style="width: 100%"  @selection-change="handleSelectionChange">
        <el-table-column
                type="selection"
                width="55">
        </el-table-column>
        <el-table-column type="expand">
            <template scope="props">
                <p>State: {{ props.row.state }}</p>
                <p>City: {{ props.row.city }}</p>
                <p>Address: {{ props.row.address }}</p>
                <p>Zip: {{ props.row.zip }}</p>
            </template>
        </el-table-column>
        <el-table-column
                label="Date"
                prop="date">
        </el-table-column>
        <el-table-column
                label="Name"
                prop="name">
        </el-table-column>
        <el-table-column
                label="City"
                prop="city">
        </el-table-column>
        <el-table-column
                label="Address"
                prop="address">
        </el-table-column>
    </el-table>
    <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage4"
            :page-sizes="[1, 10, 20, 40]"
            :page-size="1"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400">
    </el-pagination>
</div>
</@layout.main>
<#macro myPageJS>
<script>
    new Vue({
        el: '#app',
        methods: {
            handleSizeChange(val) {
                console.log(val+` items per page`);
            },
            handleCurrentChange(val) {
                console.log(`current page: `+val);
            },
            handleSelectionChange(val) {
                console.log(`select page: `+val);
            }
        },
        data: function() {
            return {
                currentPage4: 4,
                tableData3: [{
                    date: '2016-05-03',
                    name: 'Tom',
                    state: 'California',
                    city: 'Los Angeles',
                    address: 'No. 189, Grove St, Los Angeles',
                    zip: 'CA 90036'
                }, {
                    date: '2016-05-02',
                    name: 'Tom',
                    state: 'California',
                    city: 'Los Angeles',
                    address: 'No. 189, Grove St, Los Angeles',
                    zip: 'CA 90036'
                }, {
                    date: '2016-05-04',
                    name: 'Tom',
                    state: 'California',
                    city: 'Los Angeles',
                    address: 'No. 189, Grove St, Los Angeles',
                    zip: 'CA 90036'
                }, {
                    date: '2016-05-01',
                    name: 'Tom',
                    state: 'California',
                    city: 'Los Angeles',
                    address: 'No. 189, Grove St, Los Angeles',
                    zip: 'CA 90036'
                }, {
                    date: '2016-05-08',
                    name: 'Tom',
                    state: 'California',
                    city: 'Los Angeles',
                    address: 'No. 189, Grove St, Los Angeles',
                    zip: 'CA 90036'
                }, {
                    date: '2016-05-06',
                    name: 'Tom',
                    state: 'California',
                    city: 'Los Angeles',
                    address: 'No. 189, Grove St, Los Angeles',
                    zip: 'CA 90036'
                }, {
                    date: '2016-05-07',
                    name: 'Tom',
                    state: 'California',
                    city: 'Los Angeles',
                    address: 'No. 189, Grove St, Los Angeles',
                    zip: 'CA 90036'
                }]
            }
        }
    })
</script>
</#macro>