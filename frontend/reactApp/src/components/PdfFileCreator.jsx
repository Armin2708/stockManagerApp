import React from 'react';
import {Document, Page, Text, View, StyleSheet, pdf} from '@react-pdf/renderer';
import {Box, Button} from "@chakra-ui/react";
import {DownloadIcon} from "@chakra-ui/icons";

// Create styles
const styles = StyleSheet.create({
    page: {
        flexDirection: 'column',
        backgroundColor: '#fff',
        padding: 24
    },
    section: {
        margin: 10,
        padding: 10,
        flexGrow: 1
    }
});

function PdfFileCreator({client}) {
    return (
        <Document>
            <Page size="A4" style={styles.page}>
                <View style={styles.section}>
                    <Text>This is {client.firstName} {client.lastName} pdf document created in react</Text>
                </View>
            </Page>
        </Document>
    );
}

function PdfFileCreatorButton({client}) {
    const downloadPdf = async () => {
        const blob = await pdf(<PdfFileCreator client={client}/>).toBlob();
        const url = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = `${client.lastName}${client.firstName}File.pdf`;
        link.click();
    };

    return (
        <Box>
            <Button
                onClick={downloadPdf}
                _hover={{
                    transform: 'translateY(-2px)',
                    boxShadow: 'lg'
                }}
                variant={"unstyled"}
            >
                <DownloadIcon/>
            </Button>
        </Box>
    );
}

export default PdfFileCreatorButton;