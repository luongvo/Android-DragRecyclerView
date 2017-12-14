package cn.cyan.sample.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.felipecsl.asymmetricgridview.AsymmetricItem;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2017/1/4
 */
public class SampleEntity implements AsymmetricItem {

    private String text;
    private boolean dragEnable;
    private boolean dropEnable;

    private int columnSpan;
    private int rowSpan;

    public SampleEntity() {
        columnSpan = 1;
        rowSpan = 1;
    }

    public SampleEntity(Parcel in) {
        text = in.readString();
        dragEnable = in.readByte() != 0;
        dropEnable = in.readByte() != 0;
        columnSpan = in.readInt();
        rowSpan = in.readInt();
    }

    public static final Parcelable.Creator<SampleEntity> CREATOR = new Parcelable.Creator<SampleEntity>() {
        @Override
        public SampleEntity createFromParcel(@NonNull Parcel in) {
            return new SampleEntity(in);
        }

        @Override
        @NonNull
        public SampleEntity[] newArray(int size) {
            return new SampleEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeByte((byte) (dragEnable ? 1 : 0));
        dest.writeByte((byte) (dropEnable ? 1 : 0));
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDragEnable() {
        return dragEnable;
    }

    public void setDragEnable(boolean dragEnable) {
        this.dragEnable = dragEnable;
    }

    public boolean isDropEnable() {
        return dropEnable;
    }

    public void setDropEnable(boolean dropEnable) {
        this.dropEnable = dropEnable;
    }

    @Override
    public int getColumnSpan() {
        return columnSpan;
    }

    @Override
    public int getRowSpan() {
        return rowSpan;
    }

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }
}
