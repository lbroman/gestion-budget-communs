/*
 *   Copyright 2012 Hai Bison
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package android.finances.terrier.com.budget.lockpattern.util;

import android.content.Context;
import android.finances.terrier.com.budget.lockpattern.widget.LockPatternView;

import java.util.List;

/**
 * Interface for encrypter.
 *
 * @author Hai Bison
 * @since v2 beta
 */
public interface IEncrypter {

    /**
     * Encrypts {@code pattern}.
     *
     * @param context the context.
     * @param pattern the pattern in the form of a list of {@link android.finances.terrier.com.budget.lockpattern.widget.LockPatternView.Cell}.
     * @return the encrypted char array of the pattern.
     * @since v2.1 beta
     */
    char[] encrypt(Context context, List<LockPatternView.Cell> pattern);

    /**
     * Decrypts an encrypted pattern.
     *
     * @param context          the context.
     * @param encryptedPattern the encrypted pattern.
     * @return the original pattern.
     */
    List<LockPatternView.Cell> decrypt(Context context, char[] encryptedPattern);

}