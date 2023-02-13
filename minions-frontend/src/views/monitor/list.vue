<template>
    <div class="app-container">
        <el-row :gutter="20">
            <el-col :span="12" :offset="0" class="card-box">
                <el-card>
                    <template #header><span>CPU</span></template>
                    <div class="el-table el-table--enable-row-hover el-table--medium">
                        <table cellspacing="0" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th class="el-table__cell is-leaf">
                                        <div class="cell">属性</div>
                                    </th>
                                    <th class="el-table__cell is-leaf">
                                        <div class="cell">值</div>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">核心数</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.cpu">{{ servers.cpu.cpuNum }}</div>
                                    </td>
                                </tr>
                                <!-- <tr>
                  <td class="el-table__cell is-leaf">
                    <div class="cell">总数</div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="servers.cpu">{{ servers.cpu.total }}</div>
                  </td>
                </tr> -->
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">用户使用率</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.cpu">{{ servers.cpu.used }}%</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">系统使用率</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.cpu">{{ servers.cpu.sys }}%</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">当前空闲率</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.cpu">{{ servers.cpu.free }}%</div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12" :offset="0" class="card-box">
                <el-card>
                    <template #header><span>内存</span></template>
                    <div class="el-table el-table--enable-row-hover el-table--medium">
                        <table cellspacing="0" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th class="el-table__cell is-leaf">
                                        <div class="cell">属性</div>
                                    </th>
                                    <th class="el-table__cell is-leaf">
                                        <div class="cell">内存</div>
                                    </th>
                                    <th class="el-table__cell is-leaf">
                                        <div class="cell">jvm</div>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">总内存大小</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.mem">{{ servers.mem.total }}G</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.jvm">{{ servers.jvm.total }}M</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">已使用内存大小</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.mem">{{ servers.mem.used }}G</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.jvm">{{ servers.jvm.used }}M</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">剩余内存大小</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.mem">{{ servers.mem.free }}G</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.jvm">{{ servers.jvm.free }}M</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">使用率</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.mem"
                                            :class="{ 'text-danger': servers.mem.usage > 80 }">{{
                                                servers.mem.usage
                                            }}%</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.jvm"
                                            :class="{ 'text-danger': servers.jvm.usage > 80 }">{{
                                                servers.jvm.usage
                                            }}%</div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="24">
                <el-card>
                    <template #header><span>服务器信息</span></template>
                    <div class="el-table el-table--enable-row-hover el-table--medium">
                        <table cellspacing="0" style="width: 100%;">
                            <tbody>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">服务器名称</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.sys">{{ servers.sys.computerName }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">操作系统</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.sys">{{ servers.sys.osName }}</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">服务器ip</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.sys">{{ servers.sys.computerIp }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">系统架构</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" v-if="servers.sys">{{ servers.sys.osArch }}</div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="24">
                <el-card>
                    <template #header>
                        <div>Java虚拟机信息</div>
                    </template>
                    <div class="el-table el-table--enable-row-hover el-table--medium">
                        <table cellspacing="0" style="width: 100%;table-layout:fixed;">
                            <tr>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell">Java名称</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell" v-if="servers.jvm">{{ servers.jvm.name }}</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell">Java版本</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell" v-if="servers.jvm">{{ servers.jvm.version }}</div>
                                </td>
                            </tr>
                            <tr>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell">启动时间</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell" v-if="servers.jvm">{{ servers.jvm.startTime }}</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell">运行时长</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell" v-if="servers.jvm">{{ servers.jvm.runTime }}</div>
                                </td>
                            </tr>
                            <tr>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell">安装路径</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell" v-if="servers.jvm">{{ servers.jvm.home }}</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell">项目路径</div>
                                </td>
                                <td class="el-table__cell is-leaf">
                                    <div class="cell" v-if="servers.sys">{{ servers.sys.userDir }}</div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="1" class="el-table__cell is-leaf">
                                    <div class="cell">运行参数</div>
                                </td>
                                <td colspan="3" class="el-table__cell is-leaf">
                                    <div class="cell" v-if="servers.jvm">{{ servers.jvm.inputArgs }}</div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </el-card>
            </el-col>
            <el-col>
                <el-card>
                    <template #header>
                        <div>磁盘信息</div>
                    </template>
                    <div class="el-table el-table--enable-row-hover el-table--medium">
                        <table cellspacing="0" style="width: 100%;">
                            <thead>
                                <th class="el-table__cell el-table__cell is-leaf">
                                    <div>磁盘路径</div>
                                </th>
                                <th class="el-table__cell is-leaf">
                                    <div>文件系统</div>
                                </th>
                                <th class="el-table__cell is-leaf">
                                    <div>磁盘类型</div>
                                </th>
                                <th class="el-table__cell is-leaf">
                                    <div>总大小</div>
                                </th>
                                <th class="el-table__cell is-leaf">
                                    <div>可用大小</div>
                                </th>
                                <th class="el-table__cell is-leaf">
                                    <div>已用大小</div>
                                </th>
                                <th class="el-table__cell is-leaf">
                                    <div>已用百分比</div>
                                </th>
                            </thead>
                            <tbody v-if="servers.sysFiles">
                                <tr v-for="(sysFile, index ) of servers.sysFiles" :key="index">
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">{{ sysFile.dirName }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">{{ sysFile.sysTypeName }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">{{ sysFile.typeName }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">{{ sysFile.total }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">{{ sysFile.free }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell">{{ sysFile.used }}</div>
                                    </td>
                                    <td class="el-table__cell is-leaf">
                                        <div class="cell" :class="{ 'text-danger': sysFile.usage > 80 }">{{
                                            sysFile.usage
                                        }}%</div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>

</template>

<script>
import api from '@/api/monitor/monitor'

export default {
    data() {
        return {
            servers: []
        }
    },
    // 生命周期函数：内存准备完毕，页面尚未渲染
    created() {
        this.getServerInfo();
    },
    methods: {
        getServerInfo() {
            api.getServerInfo().then(
                resp => {
                    this.servers = resp.data
                }
            )
        }
    }
}


</script>