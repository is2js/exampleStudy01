package board;

public class Pagination {
    int pageCountPerBlock = 5;
    int itemCountPerPage = 3;
    int currentPageNo = 1;
    // int currentBlockNo = getCurrentBlockNo();
    // int startPageNoInBlock = getStartPageNoInBlock();
    // int endPageNoInBlock = getEndPageNoInBlock();
    // int startIndex = getStartIndex();
    // int endIndex = getEndIndex();

    public int getCurrentBlockNo() {
        return (int)Math.ceil((double)currentPageNo / pageCountPerBlock);
    }

    public int getStartPageNoInBlock() {
        return pageCountPerBlock * (getCurrentBlockNo() - 1) + 1;
    }
    public int getEndPageNoInBlock() {
        return getStartPageNoInBlock() + (pageCountPerBlock - 1);
    }

    public int getStartIndex() {
        return itemCountPerPage * (currentPageNo - 1);
    }

    public int getEndIndex() {
        return getStartIndex() + itemCountPerPage;
    }
}


