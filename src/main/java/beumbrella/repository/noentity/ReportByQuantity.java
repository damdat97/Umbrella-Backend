package beumbrella.repository.noentity;

public interface ReportByQuantity {
    public Long getId();
    public int getQuantity();
    public int getStatus();
    public String getBillId();
    public Long getShopId();
    public Long getUserId();
    public Long getProduct();
}
