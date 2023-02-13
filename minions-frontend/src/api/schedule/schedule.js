import request from '@/utils/request';

const api_name = '/schedule';

export default {
  getAllFieldNameByTableName(databaseName,tableName){
    return request({
      url: `${api_name}/getAllFields/${databaseName}/${tableName}`,
      method: 'post',
      data:{databaseName, tableName}
    })
  },
  getAllTableByDatabase(databaseName){
    return request({
      url: `${api_name}/getAllTableByDatabase/${databaseName}`,
      method: 'post',
      data:{databaseName}
    })
  },
  getAllDatabases(){
    return request({
      url: `${api_name}/database`,
      method: 'get'
    })
  },
  getAllJobs() {
    return request({
      url: `${api_name}/getAllJobs`,
      method: 'get'
    })
  },
  deleteJob(id, jobName, jobGroup) {
    return request({
      url: `${api_name}/deleteJob`,
      method: 'post',
      data: { id, jobName, jobGroup }
    })
  },
  pauseJob(jobName, jobGroup) {
    return request({
      url: `${api_name}/pauseJob`,
      method: 'post',
      data: { jobName, jobGroup }
    })
  },
  resumeJob(jobName, jobGroup) {
    return request({
      url: `${api_name}/resumeJob`,
      method: 'post',
      data: { jobName, jobGroup }
    })
  },
  createJob(data, databaseName, tableName, fieldName) {
    return request({
      url: `${api_name}/createJob/${databaseName}/${tableName}/${fieldName}`,
      method: 'post',
      data: data
    })
  }
}
