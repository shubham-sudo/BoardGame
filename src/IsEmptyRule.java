public class IsEmptyRule extends Rule {

    @Override
    public boolean check(Unit unit) {
        if (unit.isVacant()) {
            return true;
        }
        return false;
    }

}