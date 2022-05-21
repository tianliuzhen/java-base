var  data2= [
    {
        id: 1,
        label: '一级 1',
        children: [{
            id: 4,
            label: '二级 1-1',
            children: [{
                id: 9,
                label: '三级 1-1-1'
            }, {
                id: 10,
                label: '三级 1-1-2'
            }]
        }]
    },
    {
        id: 2,
        label: '一级 2',
        children: [{
            id: 5,
            label: '二级 2-1'
        }, {
            id: 6,
            label: '二级 2-2'
        }]
    },
    {
        id: 3,
        label: '一级 3',
        children: [{
            id: 7,
            label: '二级 3-1'
        }, {
            id: 8,
            label: '二级 3-2'
        }]
    }];

// 1、根据ID获取该节点的所有父节点的对象
function getParentId(list,id) {
    for (let i in list) {
        if(list[i].id==id){
            return [list[i]]
        }
        if(list[i].children){
            let node=getParentId(list[i].children,id);
            if(node!==undefined){
                return node.concat(list[i])
            }
        }
    }
}
getParentId(data2,10)//打印出来就是想要的数据

// 2、根据ID获取该节点的对象
function getId(list,id) {
    for (let i in list) {
        if(list[i].id==id){
            return [list[i]]
        }
        if(list[i].children){
            let node=getParentId(list[i].children,id);
            if(node!==undefined){
                return node;
            }
        }
    }
}

getId(data2,4)//打印出来就是想要的数据

//3、根据ID获取所有子节点的对象，首先把该节点的对象找出来，上面getId（）这个方法
function getNodeId(list,newNodeId=[]) {
    for (let i in list) {
        newNodeId.push(list[i])
        if(list[i].children){
            getNodeId(list[i].children,newNodeId);
        }
    }
    return newNodeId;
}

//查找id=4的所有子级节点
let objId=getId(data2,4);
let childId=getNodeId(objId[0]);//打印出来就是想要的数据

// 参考：https://www.cnblogs.com/wangliko/p/14271202.html
