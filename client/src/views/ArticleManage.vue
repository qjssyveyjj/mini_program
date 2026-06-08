<template>
  <div class="page-container">
    <div class="table-card">
      <div class="search-bar">
        <el-input v-model="keyword" placeholder="搜索标题" clearable style="width: 240px" />
        <el-button type="primary" @click="loadData">搜索</el-button>
        <el-button type="success" @click="openDialog()">发布文章</el-button>
      </div>
      <el-table :data="tableData" stripe border>
        <el-table-column prop="id" label="ID" width="60" />
        <el-table-column prop="title" label="标题" show-overflow-tooltip />
        <el-table-column prop="category" label="分类" width="100" />
        <el-table-column prop="viewCount" label="浏览量" width="80" />
        <el-table-column prop="status" label="状态" width="80">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'info'">{{ row.status === 1 ? '已发布' : '已下架' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="publishTime" label="发布时间" width="170" />
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="openDialog(row)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination class="pagination" v-model:current-page="page" :page-size="size" :total="total" layout="total, prev, pager, next" @current-change="loadData" />
    </div>
    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑文章' : '发布文章'" width="640px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="标题"><el-input v-model="form.title" /></el-form-item>
        <el-form-item label="分类">
          <el-select v-model="form.category" allow-create filterable>
            <el-option value="饮食健康" /><el-option value="运动健身" /><el-option value="睡眠健康" />
            <el-option value="疾病预防" /><el-option value="心理健康" /><el-option value="健康知识" />
          </el-select>
        </el-form-item>
        <el-form-item label="封面">
          <el-upload action="/api/file/upload" :show-file-list="false" :on-success="handleUpload">
            <el-button type="primary" size="small">上传封面</el-button>
          </el-upload>
          <span v-if="form.coverImage" style="margin-left:12px;color:#999">{{ form.coverImage }}</span>
        </el-form-item>
        <el-form-item label="内容"><el-input v-model="form.content" type="textarea" :rows="8" /></el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status" :active-value="1" :inactive-value="0" active-text="发布" inactive-text="下架" />
        </el-form-item>
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
  const res = await request.get('/article/page', { params: { keyword: keyword.value, page: page.value, size: size.value } })
  tableData.value = res.data.records; total.value = res.data.total
}
function openDialog(row) {
  form.value = row ? { ...row } : { status: 1, category: '健康知识' }
  dialogVisible.value = true
}
function handleUpload(res) { form.value.coverImage = res.data }
async function handleSave() {
  if (form.value.id) await request.put('/article', form.value); else await request.post('/article', form.value)
  ElMessage.success('保存成功'); dialogVisible.value = false; loadData()
}
async function handleDelete(id) {
  await ElMessageBox.confirm('确定删除？'); await request.delete(`/article/${id}`)
  ElMessage.success('删除成功'); loadData()
}
</script>
<style scoped>.pagination { margin-top: 16px; justify-content: flex-end; }</style>
