package calculator;

public class VacationPayAmount {
    private final long id;
    private final float content;

    public VacationPayAmount(long id, float content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public float getContent() {
        return content;
    }
}
