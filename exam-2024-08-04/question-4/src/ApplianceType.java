import java.util.Objects;

/*
 * @.classInvariant:
 *      name() != null
 */
public record ApplianceType(NotEmptyString name) {
    /*
     * @.pre: true
     * @.post: Object is constructed
     *      throws NullPointerException if name is null
     */
    public ApplianceType {
        Objects.requireNonNull(name, "Appliance type name should not be null");
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ApplianceType
                && ((ApplianceType) o).name().equals(name);
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
