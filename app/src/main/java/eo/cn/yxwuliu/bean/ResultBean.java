package eo.cn.yxwuliu.bean;

/**
 * Created by Administrator on 2017/5/24.
 */

public class ResultBean<T> {

    /**
     * status : 0
     * info : 成功success
         * data : [{"photo":"public\\uploads\\20170520\\4f4e0b4f554e27f514b11aba092065cd.jpg"},{"photo":"public\\uploads\\20170520\\8f88981d7cb4c7ac11cc41604004c5a1.jpg"},{"photo":"public\\uploads\\20170520\\d654ecd7e053453f5cd253ba659fd433.jpg"},{"photo":"public\\uploads\\20170520\\42d0e55441aff8251f5052e1a15f78f7.jpg"},{"photo":"public\\uploads\\20170520\\97b2d3f4f66216173a49233643488860.jpg"}]
     */

    private int status;
    private String info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {

        this.info = info;
    }

}
