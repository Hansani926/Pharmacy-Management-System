package dto;

public class BatchDTO {
    private String BatchID;
    private String BatchName;

    public BatchDTO(){
}

    public BatchDTO(String batchID, String batchName) {
        BatchID = batchID;
        BatchName = batchName;
    }
}
