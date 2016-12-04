/**
 * 项目名称：PdsuMobile1.0
 * 类名称：DipPxUtil
 * 类描述： dp与px转换
 * 创建人：suzhenpeng
 * 创建时间：2013-9-8 下午6:54:02
 * 修改人：suzhenpeng
 * 修改时间：2013-9-8 下午6:54:02
 * 修改备注：
 *
 * @version
 */
package info.smemo.nbaseaction.util;

import android.content.Context;

public class DipPxUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int pxToDip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
