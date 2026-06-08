<template>
  <div class="page-container">
    <div class="table-card">
      <div class="search-bar">
        <el-input v-model="keyword" placeholder="搜索食物/用户" clearable style="width: 240px" />
        <el-button type="primary" @click="loadData">搜索</el-button>
        <el-button type="success" @click="openDialog()">新增记录</el-button>
      </div>
      <el-table :data="tableData" stripe border>
        <el-table-column prop="id" label="ID" width="60" />
        <el-table-column prop="nickname" label="用户" width="100" />
        <el-table-column prop="mealType" label="餐别" width="80" />
        <el-table-column prop="foodName" label="食物" />
        <el-table-column prop="calories" label="热量(千卡)" width="100" />
        <el-table-column prop="recordDate" label="日期" width="110" />
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="openDialog(row)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination class="pagination" v-model:current-page="page" :page-size="size" :total="total" layout="total, prev, pager, next" @current-change="loadData" />
    </div>
    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑' : '新增'" width="480px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="用户ID"><el-input-number v-model="form.userId" :min="1" /></el-form-item>
        <el-form-item label="餐别">
          <el-select v-model="form.mealType">
            <el-option value="早餐" /><el-option value="午餐" /><el-option value="晚餐" /><el-option value="加餐" />
          </el-select>
        </el-form-item>
        <el-form-item label="食物"><el-input v-model="form.foodName" /></el-form-item>
        <el-form-item label="热量"><el-input-number v-model="form.calories" :min="0" /></el-form-item>
        <el-form-item label="日期"><el-date-picker v-model="form.recordDate" type="date" value-format="YYYY-MM-DD" /></el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '../utils/request'

const keyword = ref(''), page = ref(1), size = ref(10), total = ref(0)
const tableData = ref([]), dialogVisible = ref(false), form = ref({})

onMounted(() => loadData())
async function loadData() {
  const res = await request.get('/diet/page', { params: { keyword: keyword.value, page: page.value, size: size.value } })
  tableData.value = res.data.records; total.value = res.data.total
}
function openDialog(row) {
  form.value = row ? { ...row } : { userId: 1, mealType: '早餐', recordDate: new Date().toISOString().slice(0, 10) }
  dialogVisible.value = true
}
async function handleSave() {
  if (form.value.id) await request.put('/diet', form.value); else await request.post('/diet', form.value)
  ElMessage.success('保存成功'); dialogVisible.value = false; loadData()
}
async function handleDelete(id) {
  await ElMessageBox.confirm('确定删除？'); await request.delete(`/diet/${id}`)
  ElMessage.success('删除成功'); loadData()
}
</script>
<style scoped>.pagination { margin-top: 16px; justify-content: flex-end; }</style>
