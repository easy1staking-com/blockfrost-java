package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.TransactionMetadataLabel;
import io.blockfrost.sdk.api.model.TransactionMetadataLabelCbor;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.MetadataServiceImpl;
import io.blockfrost.sdk.impl.helper.ValidationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetadataServiceTests extends TestBase {

    MetadataService metadataService;

    @BeforeEach
    public void setup(){
        metadataService = new MetadataServiceImpl(Constants.BLOCKFROST_TESTNET_URL, projectId);
    }


    @Nested
    @DisplayName("GetTransactionMetadataLabels Tests")
    class GetTransactionMetadataLabelsTests {

        @Test
        public void transactionMetadataLabels_willReturn_transactionMetadataLabelsForCountPageAndAscendingOrder() throws APIException {

            List<TransactionMetadataLabel> expectedTransactionMetadataLabelsList = Arrays.asList(
                    TransactionMetadataLabel.builder().label("0").cip10(null).count("27471").build(),
                    TransactionMetadataLabel.builder().label("1").cip10(null).count("4454").build(),
                    TransactionMetadataLabel.builder().label("2").cip10(null).count("4185").build()
            );

            List<TransactionMetadataLabel> metadataList = metadataService.getTransactionMetadataLabels(3, 1, OrderEnum.asc);

            assertThat(metadataList, hasSize(3));
            assertThat(metadataList.get(0), samePropertyValuesAs(expectedTransactionMetadataLabelsList.get(0), "count"));

        }

        @Test
        public void transactionMetadataLabels_willReturn_transactionMetadataLabelsForCountPage() throws APIException {

            List<TransactionMetadataLabel> expectedTransactionMetadataLabelsList = Arrays.asList(
                    TransactionMetadataLabel.builder().label("0").cip10(null).count("27471").build(),
                    TransactionMetadataLabel.builder().label("1").cip10(null).count("4454").build(),
                    TransactionMetadataLabel.builder().label("2").cip10(null).count("4185").build()
            );

            List<TransactionMetadataLabel> metadataList = metadataService.getTransactionMetadataLabels(3, 1, OrderEnum.asc);

            assertThat(metadataList, hasSize(3));
            assertThat(metadataList.get(0), samePropertyValuesAs(expectedTransactionMetadataLabelsList.get(0), "count"));

        }

        @Test
        public void transactionMetadataLabels_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataLabels(101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

    }

    @Nested
    @DisplayName("GetTransactionMetadataCborForLabel Tests")
    class GetTransactionMetadataCborForLabel {

        @Test
        public void transactionMetadataCborForLabel_willReturn_transactionMetadataLabelCborForCountPageAndAscendingOrder() throws APIException {

            List<TransactionMetadataLabelCbor> expectedTransactionMetadataLabelCborList = Arrays.asList(
                    TransactionMetadataLabelCbor.builder()
                            .txHash("1c8997f9f0debde5b15fe29f0f18839a64e51c19ccdbe89e2811930d777c9b68")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("d28b574902c286dc1d589c239095c97c5f352dfac08274583898b6380274930a")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("5037dca9a80649bc44dc619233b31f4b7dcc1dd23ab808b1cc225b3b2b6bf736")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build()
            );

            List<TransactionMetadataLabelCbor> transactionMetadataLabelCborList = metadataService.getTransactionMetadataCborForLabel("0",  3, 1, OrderEnum.asc);

            assertThat(transactionMetadataLabelCborList, hasSize(3));
            assertThat(transactionMetadataLabelCborList, contains(expectedTransactionMetadataLabelCborList.toArray()));

        }

        @Test
        public void transactionMetadataCborForLabel_willReturn_transactionMetadataLabelCborForCountPage() throws APIException {

            List<TransactionMetadataLabelCbor> expectedTransactionMetadataLabelCborList = Arrays.asList(
                    TransactionMetadataLabelCbor.builder()
                            .txHash("1c8997f9f0debde5b15fe29f0f18839a64e51c19ccdbe89e2811930d777c9b68")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("d28b574902c286dc1d589c239095c97c5f352dfac08274583898b6380274930a")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build(),
                    TransactionMetadataLabelCbor.builder()
                            .txHash("5037dca9a80649bc44dc619233b31f4b7dcc1dd23ab808b1cc225b3b2b6bf736")
                            .cborMetadata("\\xa1006763617264616e6f")
                            .build()
            );

            List<TransactionMetadataLabelCbor> transactionMetadataLabelCborList = metadataService.getTransactionMetadataCborForLabel("0", 3, 1, OrderEnum.asc);

            assertThat(transactionMetadataLabelCborList, hasSize(3));
            assertThat(transactionMetadataLabelCborList, contains(expectedTransactionMetadataLabelCborList.toArray()));

        }

        @Test
        public void transactionMetadataCborForLabel_willThrowAPIException_onCountGreaterThan100() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataCborForLabel("0", 101, 1));
            assertThat(exception.getMessage(), containsString(ValidationHelper.COUNT_VALIDATION_MESSAGE));

        }

        @Test
        public void transactionMetadataCborForLabel_willThrowAPIException_onNullLabel() {

            Exception exception = assertThrows(APIException.class, () -> metadataService.getTransactionMetadataCborForLabel(null));
            assertThat(exception.getMessage(), is("Label cannot be null or empty"));
        }

    }

}
