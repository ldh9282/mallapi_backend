package org.zerock.mallapi.common.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequsetDTO {

	@Builder.Default
	private int page = 1;
	@Builder.Default
	private int size = 10;
}
