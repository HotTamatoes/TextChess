import java.util.ArrayList;
/**
 * Builds all the black pieces and adds them to the pieces
 * 
 * @author Cameron Holland 
 * @version 28/3/17
 */
public class Black
{
    Piece rook1 = new Piece("| BRook |", 5);
    Piece knight1 = new Piece("|BKnight|", 3);
    Piece bishop1 = new Piece("|BBishop|", 3);
    Piece queen = new Piece("|BQueen |", 9);
    Piece king = new Piece("| BKing |", 1);
    Piece bishop2 = new Piece("|BBishop|", 3);
    Piece knight2 = new Piece("|BKnight|", 3);
    Piece rook2 = new Piece("| BRook |", 5);
    Piece pawn1 = new Piece("| BPawn |", 1);
    Piece pawn2 = new Piece("| BPawn |", 1);
    Piece pawn3 = new Piece("| BPawn |", 1);
    Piece pawn4 = new Piece("| BPawn |", 1);
    Piece pawn5 = new Piece("| BPawn |", 1);
    Piece pawn6 = new Piece("| BPawn |", 1);
    Piece pawn7 = new Piece("| BPawn |", 1);
    Piece pawn8 = new Piece("| BPawn |", 1);
    public Black(ArrayList<Piece> pieces, ArrayList<Boolean> isBlackPiece)
    {
        addBlackPiecesTo(pieces, isBlackPiece);
    }
    
    public void addPiece(int spot, Piece item, ArrayList<Piece> pieces)
    {
        pieces.add(spot, item);
    }
    public void showisBlackPiece(int spot, boolean troo, ArrayList<Boolean> isBlackPiece)
    {
        isBlackPiece.set(spot, troo);
    }
    
    public void addBlackPiecesTo(ArrayList<Piece> pieces, ArrayList<Boolean> isBlackPiece)
    {
        addPiece(0, rook1, pieces);
        showisBlackPiece(0, true, isBlackPiece);
        addPiece(1, knight1, pieces);
        showisBlackPiece(1, true, isBlackPiece);
        addPiece(2, bishop1, pieces);
        showisBlackPiece(2, true, isBlackPiece);
        addPiece(3, queen, pieces);
        showisBlackPiece(3, true, isBlackPiece);
        addPiece(4, king, pieces);
        showisBlackPiece(4, true, isBlackPiece);
        addPiece(5, bishop2, pieces);
        showisBlackPiece(5, true, isBlackPiece);
        addPiece(6, knight2, pieces);
        showisBlackPiece(6, true, isBlackPiece);
        addPiece(7, rook2, pieces);
        showisBlackPiece(7, true, isBlackPiece);
        addPiece(8, pawn1, pieces);
        showisBlackPiece(8, true, isBlackPiece);
        addPiece(9, pawn2, pieces);
        showisBlackPiece(9, true, isBlackPiece);
        addPiece(10, pawn3, pieces);
        showisBlackPiece(10, true, isBlackPiece);
        addPiece(11, pawn4, pieces);
        showisBlackPiece(11, true, isBlackPiece);
        addPiece(12, pawn5, pieces);
        showisBlackPiece(12, true, isBlackPiece);
        addPiece(13, pawn6, pieces);
        showisBlackPiece(13, true, isBlackPiece);
        addPiece(14, pawn7, pieces);
        showisBlackPiece(14, true, isBlackPiece);
        addPiece(15, pawn8, pieces);
        showisBlackPiece(15, true, isBlackPiece);
    }
}
