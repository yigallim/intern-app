/**
 * @author Lim Yuet Yang
 */
package com.tarumt.entity.location;

import com.tarumt.utility.common.Strings;

public enum State {
    JOHOR,
    KEDAH,
    KELANTAN,
    LABUAN,
    MELAKA,
    NEGERI_SEMBILAN,
    PAHANG,
    PENANG,
    PERAK,
    PERLIS,
    PUTRAJAYA,
    SABAH,
    SARAWAK,
    SELANGOR,
    TERENGGANU,
    WP_KUALA_LUMPUR;

    @Override
    public String toString() {
        return Strings.constantCaseToTitleCase(this.name());
    }
}
