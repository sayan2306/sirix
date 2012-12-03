package org.sirix.xquery.stream;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.brackit.xquery.xdm.AbstractTemporalNode;
import org.brackit.xquery.xdm.DocumentException;
import org.brackit.xquery.xdm.Stream;
import org.sirix.api.Axis;
import org.sirix.axis.AbstractTemporalAxis;
import org.sirix.xquery.node.DBCollection;
import org.sirix.xquery.node.DBNode;

import com.google.common.base.Objects;

/**
 * {@link Stream}, wrapping a temporal axis.
 * 
 * @author Johannes Lichtenberger
 * 
 */
public class TemporalSirixStream implements Stream<AbstractTemporalNode<DBNode>> {

	private final AbstractTemporalAxis mAxis;
	private DBCollection mCollection;

	/**
	 * Constructor.
	 * 
	 * @param axis
	 *          Sirix {@link Axis}
	 * @param collection
	 *          {@link DBCollection} the nodes belong to
	 */
	public TemporalSirixStream(final @Nonnull AbstractTemporalAxis axis,
			final @Nonnull DBCollection collection) {
		mAxis = checkNotNull(axis);
		mCollection = checkNotNull(collection);
	}
	
	@Override
	public AbstractTemporalNode<DBNode> next() throws DocumentException {
		if (mAxis.hasNext()) {
			mAxis.next();
			return new DBNode(mAxis.getTrx(), mCollection);
		}
		return null;
	}

	@Override
	public void close() {
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("axis", mAxis).toString();
	}
}