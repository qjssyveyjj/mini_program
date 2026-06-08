import { get } from '../../utils/request'

/**
 * 文章详情页
 */
Page({
  data: { article: null as any },

  onLoad(options: any) {
    if (options.id) this.loadArticle(options.id)
  },

  async loadArticle(id: string) {
    try {
      const res: any = await get(`/article/${id}`, { view: true })
      this.setData({ article: res.data })
    } catch (e) { /* handled */ }
  }
})
