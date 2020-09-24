package common.dal.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author elf
 */
@MappedSuperclass
public class BaseMarkDelete extends BaseIdEntity implements MarkDelete {
    @Basic(optional = false)
    @Column(name = "delete_flag", nullable = false)
    private int deleteFlag;
    
    public BaseMarkDelete(){
        super();
    }
    
    public BaseMarkDelete(Long id) {
        super(id);
    }
    
    public BaseMarkDelete(Long id, int deleteFlag) {
        super(id);
        this.deleteFlag = deleteFlag;
    }

    @Override
    public int getDeleteFlag() {
        return this.deleteFlag;
    }

    @Override
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
