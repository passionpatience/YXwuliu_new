package eo.cn.yxwuliu.presenter;

import eo.cn.yxwuliu.base.BasePresenter;
import eo.cn.yxwuliu.view.IMsgView;

/**
 * @author jk
 * @time 2017/5/31  15:42
 * @desc ${TODD}
 */
public class MsgPresenter extends BasePresenter<IMsgView> {

    private IMsgView mMsgView;

    public MsgPresenter(IMsgView msgView) {
        this.mMsgView = msgView;
    }
}
