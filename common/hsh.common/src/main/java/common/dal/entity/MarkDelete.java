package common.dal.entity;

/**
 *
 * @author elf
 */
public interface MarkDelete extends Identity {
    
    public int getDeleteFlag();
    public void setDeleteFlag(int deleteFlag);
}
