package converter.base;

public interface Converter <SRC,DST>{
    SRC to(DST dst);
    DST from(SRC src);
}
