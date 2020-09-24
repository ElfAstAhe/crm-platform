package common.dal.entity;

/**
 *
 * @author elf
 */
public interface MarkDelete extends IdEntity {
    int getDeleteFlag();
    void setDeleteFlag(int deleteFlag);
}
