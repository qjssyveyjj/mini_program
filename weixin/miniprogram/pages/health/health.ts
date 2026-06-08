import { get, post } from '../../utils/request'

/**
 * 健康记录页面（档案/饮食/运动/睡眠）
 */
Page({
  data: {
    tabs: ['健康档案', '饮食记录', '运动记录', '睡眠记录'],
    activeTab: 0,
    healthList: [] as any[],
    dietList: [] as any[],
    exerciseList: [] as any[],
    sleepList: [] as any[],
    isEmpty: true,
    showForm: false,
    formData: {} as any,
    mealTypes: ['早餐', '午餐', '晚餐', '加餐']
  },

  onShow() {
    const user = wx.getStorageSync('user')
    if (!user) { wx.redirectTo({ url: '/pages/login/login' }); return }
    this.userId = user.id
    this.loadList()
  },

  userId: 0,

  switchTab(e: any) {
    this.setData({ activeTab: e.currentTarget.dataset.index })
    this.loadList()
  },

  async loadList() {
    const { activeTab } = this.data
    const apis = ['/health-record/page', '/diet/page', '/exercise/page', '/sleep/page']
    const keys = ['healthList', 'dietList', 'exerciseList', 'sleepList']
    try {
      const res: any = await get(apis[activeTab], { userId: this.userId, page: 1, size: 50 })
      const list = res.data.records || []
      this.setData({ [keys[activeTab]]: list, isEmpty: list.length === 0 })
    } catch (e) { /* handled */ }
  },

  showAddForm() {
    this.setData({ showForm: true, formData: { mealType: '早餐', quality: 3 } })
  },

  hideForm() { this.setData({ showForm: false }) },

  onFormInput(e: any) {
    const field = e.currentTarget.dataset.field
    this.setData({ [`formData.${field}`]: e.detail.value })
  },

  onMealChange(e: any) {
    const mealTypes = this.data.mealTypes
    this.setData({ 'formData.mealType': mealTypes[e.detail.value] })
  },

  onSleepStartChange(e: any) { this.setData({ 'formData.sleepStart': e.detail.value }) },
  onSleepEndChange(e: any) { this.setData({ 'formData.sleepEnd': e.detail.value }) },

  async submitForm() {
    const { activeTab, formData } = this.data
    const today = new Date().toISOString().slice(0, 10)
    const apis = ['/health-record', '/diet', '/exercise', '/sleep']
    const data = { ...formData, userId: this.userId, recordDate: today }
    try {
      await post(apis[activeTab], data)
      wx.showToast({ title: '添加成功', icon: 'success' })
      this.setData({ showForm: false })
      this.loadList()
    } catch (e) { /* handled */ }
  }
})
