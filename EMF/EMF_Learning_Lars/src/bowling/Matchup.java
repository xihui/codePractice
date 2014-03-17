/**
 */
package bowling;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Matchup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link bowling.Matchup#getGames <em>Games</em>}</li>
 * </ul>
 * </p>
 *
 * @see bowling.BowlingPackage#getMatchup()
 * @model
 * @generated
 */
public interface Matchup extends EObject {
	/**
	 * Returns the value of the '<em><b>Games</b></em>' reference list.
	 * The list contents are of type {@link bowling.Game}.
	 * It is bidirectional and its opposite is '{@link bowling.Game#getMatchup <em>Matchup</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Games</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Games</em>' reference list.
	 * @see bowling.BowlingPackage#getMatchup_Games()
	 * @see bowling.Game#getMatchup
	 * @model opposite="matchup" upper="2"
	 * @generated
	 */
	EList<Game> getGames();

} // Matchup
