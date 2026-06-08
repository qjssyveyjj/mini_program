import { get, post } from '../../utils/request'

/**
 * 健康提醒页面
 */
Page({
  data: {
    list: [] as any[],
    showForm: false,
    formData: {} as any,
    reminderTypes: ['喝水', '吃药', '体检', '运动']
  },

  userId: 0,

  onShow() {
    const user = wx.getStorageSync('user')
    if (!user) { wx.redirectTo({ url: '/pages/login/login' }); return }
    this.userId = user.id
    this.loadList()
  },

  async loadList() {
    try {
      const res: any = await get('/reminder/page', { userId: this.userId, page: 1, size: 50 })
      this.setData({ list: res.data.records || [] })
    } catch (e) { /* handled */ }
  },

  showAddForm() { this.setData({ showForm: true, formData: { reminderType: '喝水', isEnabled: 1 } }) },
  hideForm() { this.setData({ showForm: false }) },
  onInput(e: any) { this.setData({ [`formData.${e.currentTarget.dataset.field}`]: e.detail.value }) },
  onTypeChange(e: any) { this.setData({ 'formData.reminderType': this.data.reminderTypes[e.detail.value] }) },
  onTimeChange(e: any) { this.setData({ 'formData.remindTime': e.detail.value + ':00' }) },

  async submitForm() {
    try {
      await post('/reminder', { ...this.data.formData, userId: this.userId })
      wx.showToast({ title: '添加成功', icon: 'success' })
      this.setData({ showForm: false })
      this.loadList()
    } catch (e) { /* handled */ }
  }
})
