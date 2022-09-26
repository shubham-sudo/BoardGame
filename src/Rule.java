public abstract class Rule implements IRule {
    private static int _id = 0;
    private int ruleId;

    public Rule() {
        this.ruleId = (++_id);
    }

    public String toString() {
        return "Rule<" + this.ruleId + ", " + this.getClass().getSimpleName() + ">";
    }
}