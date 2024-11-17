import {
    Card,
    CardBody,
    Heading,
    Stack,
    Text,
    CardHeader, Tr, Td, Tbody, Checkbox, Th,
} from "@chakra-ui/react";
import UpdateClientButton from "./UpdateClientButton.jsx";
import React from "react";
import DetailsClientButton from "./DetailsClientButton.jsx";
import UploadFileButton from "./UploadFileButton.jsx";
import PdfFileCreator from "./PdfFileCreator.jsx";

export default function ClientCard({client, setSelectedIds,selectedIds}) {

    const userGender = client.gender ==="MALE"  ? "Mr." : "Mme.";

    const handleCheck = (id)=>{
        if (!selectedIds.includes(id)) {
            setSelectedIds([...selectedIds, id]);
        }
    }

    const handleUnCheck = (id) =>{
        setSelectedIds(selectedIds.filter(selectedId => selectedId !== id));    }

    const event = (e) => {
        if (e.target.checked) {
            handleCheck(client.id);
        } else {
            handleUnCheck(client.id);
        }
    }
    return (
        <>
            <Tr>
                <Td>
                    <Checkbox onChange={event} isChecked={selectedIds.includes(client.id)}/>
                </Td>
                <Td>{userGender}</Td>
                <Td>{client.firstName.toUpperCase()} {client.lastName.toUpperCase()}</Td>
                <Td isNumeric>{client.country.toUpperCase()}</Td>
                <Td>
                    <UpdateClientButton
                        client={client}
                    />
                    <DetailsClientButton
                        client={client}
                    />
                    <UploadFileButton
                        client={client}
                    />
                    <PdfFileCreator
                        client={client}
                    />
                </Td>

            </Tr>
        </>
    );
}