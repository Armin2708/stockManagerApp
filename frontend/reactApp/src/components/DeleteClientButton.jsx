import React from "react";
import {
    AlertDialog,
    AlertDialogBody,
    AlertDialogContent,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogOverlay,
    Button,
    useDisclosure,
} from "@chakra-ui/react";
import { errorNotification, successNotification } from "../services/notification.js";
import {deleteInsuranceClient} from "../services/insuranceClient.js";
import {DeleteIcon} from "@chakra-ui/icons";

function DeleteClientButton({ client, disable, setSelectedIds, selectedIds }) {
    const { isOpen, onOpen, onClose } = useDisclosure();
    const cancelRef = React.useRef();

    const handleDelete = async () => {
        if (selectedIds.length===1) {
            try {
                // Call the deleteCountry function with the given id
                await deleteInsuranceClient(selectedIds[0]).then(() => {
                    setSelectedIds((selectedIds) =>
                        selectedIds.filter((clientId) => clientId !== selectedIds[0])
                    )
                })
                // Display success notification
                successNotification(`client deleted successfully.`);
                // Close the popup after successful deletion
                onClose();
                window.location.reload();
            } catch (error) {
                // Display error notification if deletion fails
                errorNotification("Failed to delete the client.");
            }
        }
        if (selectedIds.length>1){
            try {
                await Promise.all(selectedIds.map(selectedId =>
                    deleteInsuranceClient(selectedId)
                        .then(() => {
                            setSelectedIds((selectedIds) =>
                                selectedIds.filter((clientId) => clientId !== selectedId)
                            )
                        })
                ))
                successNotification(`Selected clients deleted successfully.`);
                onClose();
                window.location.reload();
            }catch (error) {
                // Display error notification if deletion fails
                errorNotification("Failed to delete the client.");
            }
        }
    };

    return (
        <>
            <Button
                _hover={{
                    transform: 'translateY(-2px)',
                    boxShadow: 'lg'
                }}
                onClick={onOpen}
                isDisabled={disable}
            >
                {<DeleteIcon/>}
            </Button>

            <AlertDialog isOpen={isOpen} leastDestructiveRef={cancelRef} onClose={onClose}>
                <AlertDialogOverlay>
                    <AlertDialogContent>
                        <AlertDialogHeader fontSize="lg" fontWeight="bold">
                            Delete {selectedIds.length===1 ? `client` : "clients" } ?
                        </AlertDialogHeader>

                        <AlertDialogBody>
                            Are you sure? You can't undo this action afterwards.
                        </AlertDialogBody>

                        <AlertDialogFooter>
                            <Button ref={cancelRef} onClick={onClose}>
                                Cancel
                            </Button>
                            <Button colorScheme="red" onClick={handleDelete} ml={3}>
                                Delete
                            </Button>
                        </AlertDialogFooter>
                    </AlertDialogContent>
                </AlertDialogOverlay>
            </AlertDialog>
        </>
    );
}

export default DeleteClientButton;
