import request from '@/utils/request'

// 查询敏感词列表
export function listWord(query) {
  return request({
    url: '/word/word/list',
    method: 'get',
    params: query
  })
}

// 查询敏感词详细
export function getWord(id) {
  return request({
    url: '/word/word/' + id,
    method: 'get'
  })
}

// 新增敏感词
export function addWord(data) {
  return request({
    url: '/word/word',
    method: 'post',
    data: data
  })
}

// 修改敏感词
export function updateWord(data) {
  return request({
    url: '/word/word',
    method: 'put',
    data: data
  })
}

// 删除敏感词
export function delWord(id) {
  return request({
    url: '/word/word/' + id,
    method: 'delete'
  })
}
