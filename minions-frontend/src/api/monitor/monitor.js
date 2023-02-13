import request from '@/utils/request';

const api_name = '/monitor';

export default{
    getServerInfo(){
         return request({
            url: `${api_name}/server`,
            method: 'get'
    })
    }
}
