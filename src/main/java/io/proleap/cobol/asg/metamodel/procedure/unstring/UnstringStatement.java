/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.unstring;

import io.proleap.cobol.Cobol85Parser.UnstringIntoPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringSendingPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringTallyingPhraseContext;
import io.proleap.cobol.Cobol85Parser.UnstringWithPointerPhraseContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Separates data in a sending field and sends the data into multiple fields.
 */
public interface UnstringStatement extends Statement {

	IntoPhrase addIntoPhrase(UnstringIntoPhraseContext ctx);

	Sending addSending(UnstringSendingPhraseContext ctx);

	TallyingPhrase addTallyingPhrase(UnstringTallyingPhraseContext ctx);

	WithPointerPhrase addWithPointerPhrase(UnstringWithPointerPhraseContext ctx);

	IntoPhrase getIntoPhrase();

	NotOnOverflowPhrase getNotOnOverflowPhrase();

	OnOverflowPhrase getOnOverflowPhrase();

	Sending getSending();

	TallyingPhrase getTallyingPhrase();

	WithPointerPhrase getWithPointerPhrase();

	void setNotOnOverflowPhrase(NotOnOverflowPhrase notOnOverflowPhrase);

	void setOnOverflowPhrase(OnOverflowPhrase onOverflowPhrase);
}
