<template>
    <div class="app-container calendar-list-container">
        <p>我们创建的表在gmall_report数据库里面</p>
        <el-form style="width: 100%" label-position="left">
            <el-form-item label="MySQL建表语句">
                <el-input v-model="createTableString" :autosize="{ minRows: 10, maxRows: 10 }" type="textarea" />
            </el-form-item>
            <el-button size="medium" type="primary" @click="executeCreateTable()">提交</el-button>
        </el-form>
    </div>
</template>

<script>
import reportApi from '@/api/report/report'

export default {
    data: function () {
        return {
            createTableString: ""
        }
    },
    methods: {
        // 获得Tasktag列表
        executeCreateTable() {
            reportApi.executeCreateMySQLTable({sql: this.createTableString}).then(response => {
                console.log(response);
            })
        }
    },
}
</script>