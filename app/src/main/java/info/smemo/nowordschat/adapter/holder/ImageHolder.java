package info.smemo.nowordschat.adapter.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.tencent.TIMImage;
import com.tencent.TIMImageElem;
import com.tencent.TIMImageType;

import info.smemo.nowordschat.activity.ShowImageActivity;

public class ImageHolder extends ChatHolder {

    public ImageHolder(Context context, View itemView) {
        super(context, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowImageActivity.class);
                TIMImageElem imageElem = elem.getTimImageElem();
                for (final TIMImage image : imageElem.getImageList()) {
                    if (image.getType() == TIMImageType.Thumb) {
                        intent.putExtra("Thumb", image.getUrl());
                    } else if (image.getType() == TIMImageType.Original) {
                        intent.putExtra("Original", image.getUrl());
                    } else if (image.getType() == TIMImageType.Large) {
                        intent.putExtra("Large", image.getUrl());
                    }
                }
                mContext.startActivity(intent);
            }
        });
    }
}
