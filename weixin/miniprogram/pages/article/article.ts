import { get } from '../../utils/request'

/**
 * 健康资讯列表页
 */
Page({
  data: { list: [] as any[] },

  onShow() {
    const user = wx.getStorageSync('user')
    if (!user) { wx.redirectTo({ url: '/pages/login/login' }); return }
    this.loadList()
  },

  async loadList() {
    try {
      const res: any = await get('/article/page', { status: 1, page: 1, size: 50 })
      this.setData({ list: res.data.records || [] })
    } catch (e) { /* handled */ }
  },

  goDetail(e: any) {
    wx.navigateTo({ url: `/pages/article-detail/article-detail?id=${e.currentTarget.dataset.id}` })
  }
})
